// CMPSC 461 Project 1
// Galip 
class Lexer{
    private final String letters = "abcdefghijklmnopqrstuvmxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String digits = "0123456789";    //helpin to identify digits and letters
   

    String query;
    int index = 0;
    char ch;
    
    public Lexer(String s){  //starting the lexer string
        query = s; index=0; ch = nextChar();
    }

    public Token nextToken(){
        do {
            if (Character.isLetter(ch)) {
                String id = concat (letters + digits); // keywords similiar to id so have to have checker to see what is what
                if(id.equals("SELECT")|| id.equals("WHERE")  || id.equals("SELECT") || id.equals("AND")){
                   ch = nextChar();
                    return new Token(Token.TokenType.KEYWORD,id); //KEYWORD
                   //return if value is a keyword
                }
                return new Token(Token.TokenType.ID, id);  //ID
            } else if (Character.isDigit(ch)) {
                String num = concat(digits);
                num += ch; ch = nextChar();
                if (Character.isDigit(ch)) {   //again will return the values of the class
                    num += concat(digits);
                    return new Token(Token.TokenType.FLOAT, num); //FLOAT
                } else 
                    return new Token(Token.TokenType.INVALID, num);
            } else switch (ch) {
                case ' ': 
                    ch = nextChar(); break;
                case ',': 
                    ch = nextChar();
                    return new Token(Token.TokenType.COMMA, "");  //COMMA
                case '=':
                    ch = nextChar();
                    return new Token(Token.TokenType.OPERATOR, ""); //EQUAL
                case '$':
                    return new Token(Token.TokenType.EOI, "");   //EOI
                default:
                    ch = nextChar();
                    return new Token(Token.TokenType.INVALID, 
                                     Character.toString(ch));   //INVALID
                }

        } while (true);
    }

    private char nextChar() {   //helps to move to the next char
        char ch = query.charAt(index); index = index+1;
        return ch;
    }

    private String concat (String set) {   //concats the digits and chars
        StringBuffer r = new StringBuffer("");
        do { r.append(ch); ch = nextChar();
        } while (set.indexOf(ch) >= 0);
        return r.toString();
    }

    private boolean check(char c) { //checker function
        ch = nextChar();
        if (ch == c) {ch = nextChar(); return true;}
        else return false;
    }

    public void error (String msg) {
        System.err.println("\nError: location " + index + " " + msg);
        System.exit(1);
    }

}
