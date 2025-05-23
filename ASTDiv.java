public class ASTDiv implements ASTNode {

    ASTNode left, right;

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	IValue v1 = left.eval(e);
	IValue v2 = right.eval(e);
	if (v1 instanceof VInt && v2 instanceof VInt) {
	    int i1 = ((VInt) v1).getval();
	    int i2 = ((VInt) v2).getval();
	    return new VInt(i1 / i2);
	} else {
	    throw new InterpreterError("Illegal types to / operator");
	}
    }

    public ASTDiv(ASTNode l, ASTNode r) {
	left = l;
	right = r;
    }

}
