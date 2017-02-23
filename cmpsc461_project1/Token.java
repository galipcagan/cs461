// CMPSC 461 Project 1
// Galip Cagan
public class Token {
    public enum TokenType {INT, FLOAT, ID, KEYWORD, COMMA, OPERATOR, EOI, INVALID} //tokens for the lexer

    private TokenType type;
    private String val;

    Token (TokenType t, String v) { //constructor of token type and value
        type = t; val = v;
    }

    TokenType getTokenType() {return type;}
    String getTokenValue() {return val;}

    void print () { //printing 
        String s = "";
        switch (type) {
        case INT: case FLOAT: case ID: s = val; break;
        case KEYWORD: s = val; break;
        case COMMA: s = ","; break;
        case OPERATOR: s = "="; break;
        case EOI: s = "";
        case INVALID: s = "invalid"; break;
        }
        System.out.print(s);
    }

    public static String typeToString (TokenType tp) { //type to the string
        String s = "";
        switch (tp) {
        case INT: s = "Int"; break;  
        case FLOAT: s = "Float"; break;
        case KEYWORD: s = "Keyword"; break;
        case ID: s = "ID"; break;
        case COMMA: s = "Comma"; break;
        case OPERATOR: s = "Operator"; break;
        case INVALID: s="Invalid"; break;
        }
        return s;
    }
  
}
