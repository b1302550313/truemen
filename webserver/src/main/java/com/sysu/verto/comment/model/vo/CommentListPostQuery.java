package com.sysu.verto.comment.model.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentListPostQuery {
    @NotNull
    @Min(0)
    protected Long page;
    @NotNull
    @Min(1)
    protected Long limit;

}
