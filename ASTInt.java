class ASTInt implements ASTNode  {
    int val;

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckerError {
        return new ASTTInt();
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	    return new VInt(val);                
    }

    ASTInt(int v) {
        val = v;
    }

}
