class ASTInt implements ASTNode  {
    int val;

    ASTInt(int v) {
        val = v;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	    return new VInt(val);                
    }

}
