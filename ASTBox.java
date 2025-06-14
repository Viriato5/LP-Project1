class ASTBox implements ASTNode  {
    ASTNode arg;

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        ASTType t = arg.typecheck(e);
        if (t instanceof ASTTId) {
            t = ((ASTTId)t).get(e);
        }
        return new ASTTRef(t);
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	    return new VPointer(arg.eval(e));               
    }

    ASTBox(ASTNode arg0) {
        arg = arg0;
    }
}
