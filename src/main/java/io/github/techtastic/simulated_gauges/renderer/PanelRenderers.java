package io.github.techtastic.simulated_gauges.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.logistics.factoryBoard.FactoryPanelBlock;
import dev.ryanhcode.sable.Sable;
import dev.ryanhcode.sable.physics.config.dimension_physics.DimensionPhysicsData;
import dev.ryanhcode.sable.sublevel.ClientSubLevel;
import dev.ryanhcode.sable.sublevel.SubLevel;
import io.github.techtastic.simulated_gauges.content.logistics.board.GimbalSensorPanelBehaviour;
import io.github.techtastic.simulated_gauges.content.logistics.board.VelocitySensorPanelBehaviour;
import io.github.techtastic.simulated_gauges.registry.PartialModelsRegistry;
import net.createmod.catnip.animation.AnimationTickHolder;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.liukrast.deployer.lib.helper.ClientRegisterHelpers;
import net.liukrast.deployer.lib.logistics.board.AbstractPanelBehaviour;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import static net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock.FACE;
import static net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING;

public class PanelRenderers implements ClientRegisterHelpers.PanelRenderer {

    private void renderVelocityFan(VelocitySensorPanelBehaviour velocity, ClientSubLevel sublevel, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        var model = PartialModelsRegistry.VELOCITY_PANEL_FAN;

        float speed = (float) velocity.getVectorValue();
        float angle = (AnimationTickHolder.getRenderTime() * speed) % 360f;
        float scale = 0.4f;
        BlockState blockState = velocity.panelBE().getBlockState();
        AttachFace face = blockState.getValue(FACE);
        Direction facing = blockState.getValue(FACING);
        FactoryPanelBlock.PanelSlot slot = velocity.slot;

        SuperByteBuffer superBuffer = CachedBuffers.partial(model, velocity.blockEntity.getBlockState())
                .light(light)
                .overlay(overlay)
                .scale(scale);

        Quaternionf quat = new Quaternionf();
        Vector3f offset = new Vector3f();

        switch(face) {
            case FLOOR -> {
                quat.rotateY((float) Math.toRadians(angle));
                switch(facing) {
                    case NORTH -> {
                        switch (slot) {
                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(1.375f, 0.3f, 0.125f);
                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(0.125f, 0.3f, 0.125f);
                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(1.375f, 0.3f, 1.375f);
                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(0.125f, 0.3f, 1.375f);
                        }
                    }
                    case SOUTH -> {
                        switch (slot) {
                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(0.125f, 0.3f, 1.375f);
                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(1.375f, 0.3f, 1.375f);
                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(0.125f, 0.3f, 0.125f);
                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(1.375f, 0.3f, 0.125f);
                        }
                    }
                    case WEST -> {
                        switch (slot) {
                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(0.125f, 0.3f, 0.125f);
                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(0.125f, 0.3f, 1.375f);
                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(1.375f, 0.3f, 0.125f);
                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(1.375f, 0.3f, 1.375f);
                        }
                    }
                    default -> {
                        switch (slot) {
                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(1.375f, 0.3f, 1.375f);
                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(1.375f, 0.3f, 0.125f);
                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(0.125f, 0.3f, 1.375f);
                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(0.125f, 0.3f, 0.125f);
                        }
                    }
                }
            }
            case CEILING -> {
                quat.rotateY((float) Math.toRadians(angle));
                switch(facing) {
                    case NORTH -> {
                        switch (slot) {
                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(0.125f, 2.2f, 0.125f);
                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(1.375f, 2.2f, 0.125f);
                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(0.125f, 2.2f, 1.375f);
                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(1.375f, 2.2f, 1.375f);
                        }
                    }
                    case SOUTH -> {
                        switch (slot) {
                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(1.375f, 2.2f, 1.375f);
                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(0.125f, 2.2f, 1.375f);
                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(1.375f, 2.2f, 0.125f);
                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(0.125f, 2.2f, 0.125f);
                        }
                    }
                    case WEST -> {
                        switch (slot) {
                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(0.125f, 2.2f, 1.375f);
                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(0.125f, 2.2f, 0.125f);
                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(1.375f, 2.2f, 1.375f);
                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(1.375f, 2.2f, 0.125f);
                        }
                    }
                    default -> {
                        switch (slot) {
                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(1.375f, 2.2f, 0.125f);
                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(1.375f, 2.2f, 1.375f);
                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(0.125f, 2.2f, 0.125f);
                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(0.125f, 2.2f, 1.375f);
                        }
                    }
                }
            }
            default -> {
                switch(facing) {
                    case NORTH -> {
                        quat.rotateZ((float) Math.toRadians(angle));
                        quat.rotateX((float) Math.toRadians(90));
                        switch (slot) {
                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(1.375f, 0.125f, 2.2f);
                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(0.125f, 0.125f, 2.2f);
                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(1.375f, 1.375f, 2.2f);
                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(0.125f, 1.375f, 2.2f);
                        }
                    }
                    case SOUTH -> {
                        quat.rotateZ((float) Math.toRadians(angle));
                        quat.rotateX((float) Math.toRadians(90));
                        switch (slot) {
                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(0.125f, 0.125f, 0.3f);
                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(1.375f, 0.125f, 0.3f);
                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(0.125f, 1.375f, 0.3f);
                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(1.375f, 1.375f, 0.3f);
                        }
                    }
                    case WEST -> {
                        quat.rotateX((float) Math.toRadians(angle));
                        quat.rotateZ((float) Math.toRadians(-90));
                        switch (slot) {
                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(2.2f, 0.125f, 0.125f);
                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(2.2f, 0.125f, 1.375f);
                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(2.2f, 1.375f, 0.125f);
                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(2.2f, 1.375f, 1.375f);
                        }
                    }
                    default -> {
                        quat.rotateX((float) Math.toRadians(angle));
                        quat.rotateZ((float) Math.toRadians(-90));
                        switch (slot) {
                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(0.3f, 0.125f, 1.375f);
                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(0.3f, 0.125f, 0.125f);
                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(0.3f, 1.375f, 1.375f);
                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(0.3f, 1.375f, 0.125f);
                        }
                    }
                }
            }
        }

        superBuffer
                .center()
                .translateX(offset.x())
                .translateY(offset.y())
                .translateZ(offset.z())
                .rotate(quat)
                .uncenter()
                .renderInto(ms, buffer.getBuffer(RenderType.cutoutMipped()));
    }

    private void renderGimbalCompass(GimbalSensorPanelBehaviour gimbal, ClientSubLevel sublevel, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        var temp = PartialModelsRegistry.GIMBAL_PANEL_POINTER;

        Vector3f magneticNorth = DimensionPhysicsData.of(gimbal.getWorld()).magneticNorth().orElse(Direction.NORTH.step());
        if (magneticNorth.equals(new Vector3f()))
            magneticNorth = Direction.NORTH.step();
        Quaternionf quat = new Quaternionf();
        quat.lookAlong(magneticNorth, Direction.UP.step());
        if (sublevel != null)
            quat.mul(sublevel.logicalPose().orientation().get(new Quaternionf()).invert());

        float scale = 0.6f;
        BlockState blockState = gimbal.panelBE().getBlockState();
        AttachFace face = blockState.getValue(FACE);
        Direction facing = blockState.getValue(FACING);
        FactoryPanelBlock.PanelSlot slot = gimbal.slot;

        SuperByteBuffer superBuffer = CachedBuffers.partial(temp, gimbal.blockEntity.getBlockState())
                .light(light)
                .overlay(overlay)
                .scale(scale);

        Vector3f offset = new Vector3f();

        switch (face) {
            case FLOOR -> {
                switch (facing) {
                    case EAST -> {
                        switch (slot) {
                            case BOTTOM_LEFT -> offset.set(0f, 0f, 0f);
                            case BOTTOM_RIGHT -> offset.set(0f, 0f, 0f);
                            case TOP_LEFT -> offset.set(0f, 0f, 0f);
                            case TOP_RIGHT -> offset.set(0f, 0f, 0f);
                        }
                    }
                    case SOUTH -> {
                        switch (slot) {
                            case BOTTOM_LEFT -> offset.set(-0.085f, 0.2f, 0.75f);
                            case BOTTOM_RIGHT -> offset.set(0.75f, 0.2f, 0.75f);
                            case TOP_LEFT -> offset.set(-0.085f, 0.2f, -0.085f);
                            case TOP_RIGHT -> offset.set(0.75f, 0.2f, -0.085f);
                        }
                    }
                    case WEST -> {
                        switch (slot) {
                            case BOTTOM_LEFT -> offset.set(0f, 0f, 0f);
                            case BOTTOM_RIGHT -> offset.set(0f, 0f, 0f);
                            case TOP_LEFT -> offset.set(0f, 0f, 0f);
                            case TOP_RIGHT -> offset.set(0f, 0f, 0f);
                        }
                    }
                    default -> {
                        switch (slot) {
                            case BOTTOM_LEFT -> offset.set(0.75f, 0.2f, -0.085f);
                            case BOTTOM_RIGHT -> offset.set(-0.085f, 0.2f, -0.085f);
                            case TOP_LEFT -> offset.set(0.75f, 0.2f, 0.75f);
                            case TOP_RIGHT -> offset.set(-0.085f, 0.2f, 0.75f);
                        }
                    }
                }
            }
            case CEILING -> {
                switch (facing) {
                    case EAST -> {
                        switch (slot) {
                            case BOTTOM_LEFT -> offset.set(0f, 0f, 0f);
                            case BOTTOM_RIGHT -> offset.set(0f, 0f, 0f);
                            case TOP_LEFT -> offset.set(0f, 0f, 0f);
                            case TOP_RIGHT -> offset.set(0f, 0f, 0f);
                        }
                    }
                    case SOUTH -> {
                        switch (slot) {
                            case BOTTOM_LEFT -> offset.set(0f, 0f, 0f);
                            case BOTTOM_RIGHT -> offset.set(0f, 0f, 0f);
                            case TOP_LEFT -> offset.set(0f, 0f, 0f);
                            case TOP_RIGHT -> offset.set(0f, 0f, 0f);
                        }
                    }
                    case WEST -> {
                        switch (slot) {
                            case BOTTOM_LEFT -> offset.set(0f, 0f, 0f);
                            case BOTTOM_RIGHT -> offset.set(0f, 0f, 0f);
                            case TOP_LEFT -> offset.set(0f, 0f, 0f);
                            case TOP_RIGHT -> offset.set(0f, 0f, 0f);
                        }
                    }
                    default -> {
                        switch (slot) {
                            case BOTTOM_LEFT -> offset.set(0f, 0f, 0f);
                            case BOTTOM_RIGHT -> offset.set(0f, 0f, 0f);
                            case TOP_LEFT -> offset.set(0f, 0f, 0f);
                            case TOP_RIGHT -> offset.set(0f, 0f, 0f);
                        }
                    }
                }
            }
            default -> {
                switch (facing) {
                    case EAST -> {
                        switch (slot) {
                            case BOTTOM_LEFT -> offset.set(0f, 0f, 0f);
                            case BOTTOM_RIGHT -> offset.set(0f, 0f, 0f);
                            case TOP_LEFT -> offset.set(0f, 0f, 0f);
                            case TOP_RIGHT -> offset.set(0f, 0f, 0f);
                        }
                    }
                    case SOUTH -> {
                        switch (slot) {
                            case BOTTOM_LEFT -> offset.set(0f, 0f, 0f);
                            case BOTTOM_RIGHT -> offset.set(0f, 0f, 0f);
                            case TOP_LEFT -> offset.set(0f, 0f, 0f);
                            case TOP_RIGHT -> offset.set(0f, 0f, 0f);
                        }
                    }
                    case WEST -> {
                        switch (slot) {
                            case BOTTOM_LEFT -> offset.set(0f, 0f, 0f);
                            case BOTTOM_RIGHT -> offset.set(0f, 0f, 0f);
                            case TOP_LEFT -> offset.set(0f, 0f, 0f);
                            case TOP_RIGHT -> offset.set(0f, 0f, 0f);
                        }
                    }
                    default -> {
                        switch (slot) {
                            case BOTTOM_LEFT -> offset.set(0f, 0f, 0f);
                            case BOTTOM_RIGHT -> offset.set(0f, 0f, 0f);
                            case TOP_LEFT -> offset.set(0f, 0f, 0f);
                            case TOP_RIGHT -> offset.set(0f, 0f, 0f);
                        }
                    }
                }
            }
        }

//        switch(face) {
//            case FLOOR -> {
//                switch(facing) {
//                    case NORTH -> {
//                        switch (slot) {
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(1.375f, 0.3f, 0.125f);
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(0.125f, 0.3f, 0.125f);
//                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(1.375f, 0.3f, 1.375f);
//                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(0.125f, 0.3f, 1.375f);
//                        }
//                    }
//                    case SOUTH -> {
//                        switch (slot) {
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(0.125f, 0.3f, 1.375f);
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(1.375f, 0.3f, 1.375f);
//                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(0.125f, 0.3f, 0.125f);
//                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(1.375f, 0.3f, 0.125f);
//                        }
//                    }
//                    case WEST -> {
//                        switch (slot) {
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(0.125f, 0.3f, 0.125f);
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(0.125f, 0.3f, 1.375f);
//                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(1.375f, 0.3f, 0.125f);
//                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(1.375f, 0.3f, 1.375f);
//                        }
//                    }
//                    default -> {
//                        switch (slot) {
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(1.375f, 0.3f, 1.375f);
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(1.375f, 0.3f, 0.125f);
//                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(0.125f, 0.3f, 1.375f);
//                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(0.125f, 0.3f, 0.125f);
//                        }
//                    }
//                }
//            }
//            case CEILING -> {
//                switch(facing) {
//                    case NORTH -> {
//                        switch (slot) {
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(0.125f, 2.2f, 0.125f);
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(1.375f, 2.2f, 0.125f);
//                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(0.125f, 2.2f, 1.375f);
//                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(1.375f, 2.2f, 1.375f);
//                        }
//                    }
//                    case SOUTH -> {
//                        switch (slot) {
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(1.375f, 2.2f, 1.375f);
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(0.125f, 2.2f, 1.375f);
//                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(1.375f, 2.2f, 0.125f);
//                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(0.125f, 2.2f, 0.125f);
//                        }
//                    }
//                    case WEST -> {
//                        switch (slot) {
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(0.125f, 2.2f, 1.375f);
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(0.125f, 2.2f, 0.125f);
//                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(1.375f, 2.2f, 1.375f);
//                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(1.375f, 2.2f, 0.125f);
//                        }
//                    }
//                    default -> {
//                        switch (slot) {
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(1.375f, 2.2f, 0.125f);
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(1.375f, 2.2f, 1.375f);
//                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(0.125f, 2.2f, 0.125f);
//                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(0.125f, 2.2f, 1.375f);
//                        }
//                    }
//                }
//            }
//            default -> {
//                switch(facing) {
//                    case NORTH -> {
//                        quat.rotateX((float) Math.toRadians(90));
//                        switch (slot) {
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(1.375f, 0.125f, 2.2f);
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(0.125f, 0.125f, 2.2f);
//                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(1.375f, 1.375f, 2.2f);
//                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(0.125f, 1.375f, 2.2f);
//                        }
//                    }
//                    case SOUTH -> {
//                        quat.rotateX((float) Math.toRadians(90));
//                        switch (slot) {
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(0.125f, 0.125f, 0.3f);
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(1.375f, 0.125f, 0.3f);
//                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(0.125f, 1.375f, 0.3f);
//                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(1.375f, 1.375f, 0.3f);
//                        }
//                    }
//                    case WEST -> {
//                        quat.rotateZ((float) Math.toRadians(-90));
//                        switch (slot) {
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(2.2f, 0.125f, 0.125f);
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(2.2f, 0.125f, 1.375f);
//                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(2.2f, 1.375f, 0.125f);
//                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(2.2f, 1.375f, 1.375f);
//                        }
//                    }
//                    default -> {
//                        quat.rotateZ((float) Math.toRadians(-90));
//                        switch (slot) {
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_LEFT -> offset.set(0.3f, 0.125f, 1.375f);
//                            case FactoryPanelBlock.PanelSlot.BOTTOM_RIGHT -> offset.set(0.3f, 0.125f, 0.125f);
//                            case FactoryPanelBlock.PanelSlot.TOP_LEFT -> offset.set(0.3f, 1.375f, 1.375f);
//                            case FactoryPanelBlock.PanelSlot.TOP_RIGHT -> offset.set(0.3f, 1.375f, 0.125f);
//                        }
//                    }
//                }
//            }
//        }

        superBuffer
                .center()
                .translateX(offset.x())
                .translateY(offset.y())
                .translateZ(offset.z())
                .rotate(quat)
                .uncenter()
                .renderInto(ms, buffer.getBuffer(RenderType.cutoutMipped()));
    }

    @Override
    public void render(AbstractPanelBehaviour apb, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        ClientSubLevel sublevel = Sable.HELPER.getContainingClient(apb.getPos());

        switch (apb) {
            case VelocitySensorPanelBehaviour velocity -> renderVelocityFan(velocity, sublevel, partialTicks, ms, buffer, light, overlay);
            case GimbalSensorPanelBehaviour gimbal -> renderGimbalCompass(gimbal, sublevel, partialTicks, ms, buffer, light, overlay);
            default -> {}
        }
    }
}
