public class ASTDeRef implements ASTNode {

    ASTNode exp;

	public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
		ASTType t = exp.typecheck(e);
		if (t instanceof ASTTRef) {
			return ((ASTTRef)t).getType();
		} else {
			throw new TypeCheckerError("illegal types to dereference operator (*)");
		}
	}

    public IValue eval(Environment <IValue>e) throws InterpreterError { 
		IValue val = exp.eval(e); 
		if (val instanceof VPointer) { 
			return ((VPointer) val).getValue();
		} else { 
			throw new InterpreterError("illegal types to dereference operator (*)"); 
		}
    }

	public ASTDeRef(ASTNode e) {
		exp = e;
	}
}

