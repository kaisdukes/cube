package cube.compiler;

import org.objectweb.asm.*;

public class CubeCompiler implements Opcodes {

    public byte[] compile() {

        final ClassWriter classWriter = new ClassWriter(0);
        classWriter.visit(V15, ACC_PUBLIC | ACC_SUPER, "cube/examples/Factorial", null, "java/lang/Object", null);

        classWriter.visitSource("Factorial.java", null);

        {
            final var methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(3, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lcube/examples/Factorial;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            final var methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "factorial", "(I)I", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(6, label0);
            methodVisitor.visitVarInsn(ILOAD, 0);
            Label label1 = new Label();
            methodVisitor.visitJumpInsn(IFNE, label1);
            methodVisitor.visitInsn(ICONST_1);
            Label label2 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label2);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitVarInsn(ILOAD, 0);
            methodVisitor.visitVarInsn(ILOAD, 0);
            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitInsn(ISUB);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "cube/examples/Factorial", "factorial", "(I)I", false);
            methodVisitor.visitInsn(IMUL);
            methodVisitor.visitLabel(label2);
            methodVisitor.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{Opcodes.INTEGER});
            methodVisitor.visitInsn(IRETURN);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLocalVariable("n", "I", null, label0, label3, 0);
            methodVisitor.visitMaxs(3, 1);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}