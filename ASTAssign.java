public class ASTAssign implements ASTNode {
    ASTNode left;
    ASTNode right;

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        ASTType leftType = left.typecheck(e);
        if (!(leftType instanceof ASTTRef)) {
            throw new TypeCheckerError("Incompatible type for assignment: " + leftType.toStr());
        }
        ASTTRef leftRefType = (ASTTRef) leftType;
        ASTType rightType = right.typecheck(e);
        if (!(leftRefType.getType().isSubTypeOf(rightType, e) && rightType.isSubTypeOf(leftRefType.getType(), e))) {
            throw new TypeCheckerError("Incompatible types for assignment: " + leftRefType.getType().toStr() + " and " + rightType.toStr());
        }
        return rightType;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue leftval = left.eval(e);
        if (leftval instanceof VPointer == false) {
            throw new InterpreterError("Atribuição não é possível");
        }
        IValue rightval = right.eval(e);
        VPointer p = (VPointer) leftval;
        p.setValue(rightval);
        return rightval;
    }

    public ASTAssign(ASTNode l, ASTNode r) {
        left = l;
        right = r;
    }

}
