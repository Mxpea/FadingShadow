#version 150

#define M_PI 3.1415926535897932384626433832795

#moj_import <fog.glsl>

const int particleCount = 8;      // atlas 中可用的 sprite 数量
const int particleOutOf = 101;    // 用于伪随机选择
const float lightmix = 0.2f;      // 光照混合强度（0..1）

uniform sampler2D Sampler0;       // 绑定到方块图集（或自定义 atlas）

uniform vec4 ColorModulator;      // 外部颜色调节（Java 端设置）
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

uniform float time;               // 动画时间（Java 端每帧更新）

uniform float yaw;                // 摄像机朝向（由 Java 端设置）
uniform float pitch;
uniform float externalScale;      // 缩放因子，控制细节密度

uniform float opacity;            // 全局不透明度

uniform mat2 particleUVs[particleCount]; // 每个 sprite 的 uv (min,max) (设置为 2x vec2)

in float vertexDistance;
in vec4 vertexColor;
in vec2 texCoord0;
in vec4 normal;
in vec3 fPos;

out vec4 fragColor;

// 绕任意轴旋转矩阵
mat4 rotationMatrix(vec3 axis, float angle)
{
    axis = normalize(axis);
    float s = sin(angle);
    float c = cos(angle);
    float oc = 1.0 - c;

    return mat4(
        oc * axis.x * axis.x + c,           oc * axis.x * axis.y - axis.z * s,  oc * axis.z * axis.x + axis.y * s,  0.0,
        oc * axis.x * axis.y + axis.z * s,  oc * axis.y * axis.y + c,           oc * axis.y * axis.z - axis.x * s,  0.0,
        oc * axis.z * axis.x - axis.y * s,  oc * axis.y * axis.z + axis.x * s,  oc * axis.z * axis.z + c,           0.0,
        0.0,                                0.0,                                0.0,                                1.0
    );
}

void main (void)
{
    // mask 用于把效果限制在模型 UV 指定的区域 (texCoord0)
    vec4 mask = texture(Sampler0, texCoord0.xy);

    float oneOverExternalScale = 1.0 / max(0.0001, externalScale);

    int uvtiles = 16; // 把连续 UV 划分为 16x16 瓦片以产生伪随机分布

    // 背景：非常深的紫色/黑色基底
    vec4 col = vec4(0.02, 0.00, 0.04, 1.0);

    // 微弱脉动，给紫色带来时间变化
    float pulse = mod(time, 400.0) / 400.0;
    col.g += sin(pulse * M_PI * 2.0) * 0.03; // green 微调
    col.b += cos(pulse * M_PI * 2.0) * 0.05; // blue 微调

    // 从相机到片元的方向（用于球面映射）
    vec4 dir = normalize(vec4(-fPos, 0.0));

    // 根据 pitch/yaw 调整方向（与 cosmic 类似）
    float sb = sin(pitch);
    float cb = cos(pitch);
    dir = normalize(vec4(dir.x, dir.y * cb - dir.z * sb, dir.y * sb + dir.z * cb, 0.0));

    float sa = sin(-yaw);
    float ca = cos(-yaw);
    dir = normalize(vec4(dir.z * sa + dir.x * ca, dir.y, dir.z * ca - dir.x * sa, 0.0));

    vec4 ray;

    // 多层叠加以产生厚重感（层数可调）
    for (int i = 0; i < 12; i++) {
        int mult = 12 - i;

        // 伪随机量（确定性，不会闪烁）
        int j = i + 11;
        float rand1 = (j * j * 4127 + j * 13) * 2.0;
        int k = j + 3;
        float rand2 = (k * k * k * 191 + k * 23) * 3.2;
        float rand3 = rand1 * 311.4 + rand2 * 77.3;

        // 随机轴
        vec3 axis = normalize(vec3(sin(rand1), sin(rand2), cos(rand3)));

        // 旋转射线，得到该层的映射方向
        ray = dir * rotationMatrix(axis, mod(rand3, 2.0 * M_PI));

        // 计算原始球面 uv
        float rawu = 0.5 + (atan(ray.z, ray.x) / (2.0 * M_PI));
        float rawv = 0.5 + (asin(ray.y) / M_PI);

        // 分层缩放与时间偏移（不同层移动速率、方向略有差别）
        float scale = mult * 0.45 + 2.0;
        float u = rawu * scale * externalScale;
        float v = (rawv + time * 0.00025 * oneOverExternalScale * (0.8 + fract(rand2))) * scale * 0.65 * externalScale;

        vec2 tex = vec2(u, v);

        // tile 索引
        int tu = int(mod(floor(u * uvtiles), uvtiles));
        int tv = int(mod(floor(v * uvtiles), uvtiles));

        // 伪随机选择 symbol、旋转与翻转
        int position = ((191 * tu) + (503 * tv) + (317 * (i + 17)) + 27111) ^ 31;
        int symbol = int(mod(position, particleOutOf));
        int rotation = int(mod(pow(tu, float(tv)) + tu + 5 + tv * i, 8));
        bool flip = false;
        if (rotation >= 4) {
            rotation -= 4;
            flip = true;
        }

        // 只有 symbol 在 0..particleCount-1 时才绘制该 tile
        if (symbol >= 0 && symbol < particleCount) {
            vec2 particleTex = vec2(1.0, 1.0);
            vec4 tcol = vec4(1.0, 0.0, 0.0, 1.0);

            // tile 内局部 uv
            float ru = clamp(mod(u, 1.0) * uvtiles - tu, 0.0, 1.0);
            float rv = clamp(mod(v, 1.0) * uvtiles - tv, 0.0, 1.0);

            if (flip) ru = 1.0 - ru;

            float oru = ru;
            float orv = rv;
            if (rotation == 1) {
                oru = 1.0 - rv;
                orv = ru;
            } else if (rotation == 2) {
                oru = 1.0 - ru;
                orv = 1.0 - rv;
            } else if (rotation == 3) {
                oru = rv;
                orv = 1.0 - ru;
            }

            // 从给定的 particleUVs 中读取 sprite 区域
            float umin = particleUVs[symbol][0][0];
            float umax = particleUVs[symbol][1][0];
            float vmin = particleUVs[symbol][0][1];
            float vmax = particleUVs[symbol][1][1];

            // 插值计算实际采样 uv
            particleTex.x = mix(umin, umax, oru);
            particleTex.y = mix(vmin, vmax, orv);

            tcol = texture(Sampler0, particleTex);

            // alpha 随层数与 tile 内容放大，边缘平滑衰减（避免极区堆积）
            float a = tcol.r * (0.4 + (1.0 / float(mult)) * 0.9) * (1.0 - smoothstep(0.15, 0.48, abs(rawv - 0.5)));

            // 生成紫色与接近黑的变体（紫色偏向 r:0.6~0.9, b:0.8~1.0）
            float pr = (mod(rand1, 23.0) / 23.0) * 0.3 + 0.6; // purple red component
            float pg = (mod(rand2, 19.0) / 19.0) * 0.12 + 0.02; // purple green small
            float pb = (mod(rand3, 29.0) / 29.0) * 0.2 + 0.8; // purple blue component

            // 某些粒子偏暗（near-black），用随机权重混合
            float darkChance = mod(rand2 + rand1, 7.0);
            float darkBlend = (darkChance < 1.4) ? 0.9 : 0.0; // 小概率变为暗色
            vec3 dark = vec3(0.01, 0.005, 0.02);

            vec3 particleColor = mix(vec3(pr, pg, pb), dark, darkBlend);

            // 使用加法累加产生发光感（后续乘以光照做校正）
            col.rgb += particleColor * a;
            // accumulate alpha as well but keep base 1.0 so fog/light works
            col.a = clamp(col.a + a * 0.25, 0.0, 1.0);
        }
    }

    // 应用光照贴图的 shading，使特效部分受世界光照影响
    vec3 shade = vertexColor.rgb * (lightmix) + vec3(1.0 - lightmix);
    col.rgb *= shade;

    // 通过模型 mask 和全局 opacity 控制最终 alpha
    col.a *= mask.r * opacity;

    col = clamp(col, 0.0, 1.0);

    fragColor = linear_fog(col * ColorModulator, vertexDistance, FogStart, FogEnd, FogColor);
}