public class ASTDeRef implements ASTNode {

    ASTNode exp;

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

