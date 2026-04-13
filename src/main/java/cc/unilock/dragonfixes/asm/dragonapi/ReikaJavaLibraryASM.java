package cc.unilock.dragonfixes.asm.dragonapi;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class ReikaJavaLibraryASM implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (basicClass == null || basicClass.length == 0) return basicClass;

        if ("Reika.DragonAPI.Libraries.Java.ReikaJavaLibrary".equals(transformedName)) {
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(basicClass);
            classReader.accept(classNode, ClassReader.SKIP_FRAMES);

            for (MethodNode methodNode : classNode.methods) {
                if ("getMaximumRecursiveDepth".equals(methodNode.name)) {
                    methodNode.instructions.clear();

                    methodNode.instructions.add(new LdcInsnNode(1000));
                    methodNode.instructions.add(new InsnNode(Opcodes.IRETURN));
                }
            }

            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classNode.accept(writer);
            return writer.toByteArray();
        }

        return basicClass;
    }
}
