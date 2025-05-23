public class ASTAssign implements ASTNode {
    ASTNode left;
    ASTNode right;

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
