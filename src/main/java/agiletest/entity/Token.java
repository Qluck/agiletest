package agiletest.entity;

import lombok.Data;

@Data
public class Token {
    private String token;
    private boolean isExpired;
}
