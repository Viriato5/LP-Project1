public class ASTSub implements ASTNode {

    ASTNode left, right;

    public IValue eval(Environment<IValue> e) throws InterpreterError {
		IValue v1 = left.eval(e);
		IValue v2 = right.eval(e);
		if (v1 instanceof VInt && v2 instanceof VInt) {
			return new VInt(((VInt) v1).getval() - ((VInt) v2).getval());
		} else {
			throw new InterpreterError("Illegal types to + operator");
		}
    }

    public ASTSub(ASTNode l, ASTNode r) {
		left = l;
		right = r;
    }

}
