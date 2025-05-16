package bg.sofia.uni.fmi.mjt.client.dto;

import com.google.gson.Gson;

public final class ClientRequestDto<T> {
    private String command;
    private T args;

    public ClientRequestDto(String command, T args) {
        this.command = command;
        this.args = args;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}