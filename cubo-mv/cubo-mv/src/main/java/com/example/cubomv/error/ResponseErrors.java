package com.example.cubomv.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseErrors {

    @Getter
    @Setter
    private Integer status;
    @Getter
    @Setter
    private OffsetDateTime data;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private List<Body> bodys;

    public static class Body {
        @Getter
        @Setter
        private String nome, mensagem;

        public Body(String nome, String mensagem) {
//            this.setNome(nome);
//            this.setMensagem(mensagem);
        }
    }

    public ResponseErrors(Integer status, OffsetDateTime data, String message, List<Body> bodys) {
        super();
        this.status = status;
        this.data = data;
        this.message = message;
        this.bodys = bodys;
    }

    public ResponseErrors() {
    }
}