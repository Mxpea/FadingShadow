package io.github.mxpea.fadingshadow.block;

import com.mojang.serialization.MapCodec;
import io.github.mxpea.fadingshadow.block.entity.AnimatedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class AnimatedBlock extends BaseEntityBlock {
    // 水平朝向属性：北南东西
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE = BaseEntityBlock.box(
            4.0D, 0.0D, 4.0D,
            12.0D, 18.0D, 12.0D
    );

    public AnimatedBlock(Properties properties) {
        super(properties);
        // 设置默认方块状态：面向北
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new AnimatedBlockEntity(blockPos, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    // 让 BlockState 支持 FACING 属性
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    // 放置时根据玩家方向设置方块朝向
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // 让方块“正面”朝向玩家：取玩家朝向的相反方向
        Direction dir = context.getHorizontalDirection().getOpposite();
        return this.defaultBlockState().setValue(FACING, dir);
    }

    @Override
    public VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context
    ) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context
    ) {
        return SHAPE;
    }
}