package io.github.mxpea.fadingshadow.block;

import io.github.mxpea.fadingshadow.item.ModItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChiliCropBlock extends CropBlock {
    public static final int MAX_AGE =3;
    public static final IntegerProperty AGE =IntegerProperty.create("age",0,3);
    public static final VoxelShape[] SHAPE_BY_AGE =new VoxelShape[]{
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F),
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F),
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F),
            Block.box(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F)
    };

    public ChiliCropBlock(Properties properties){
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context){
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    protected ItemLike getBaseSeedId(){
        return ModItem.chili_seed;
    }

    @Override
    protected IntegerProperty getAgeProperty(){
        return AGE;
    }

    @Override
    public int getMaxAge(){
        return MAX_AGE;
    }


    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
        // 检查是否为耕地（Farmland）
        return state.is(Blocks.FARMLAND);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        builder.add(AGE);
    }

}
