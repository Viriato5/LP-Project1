class ASTBool implements ASTNode  {
    boolean val;

    ASTBool(boolean v) {
        val = v;
    }

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        return new ASTTBool();
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	    return new VBool(val);                
    }
}
