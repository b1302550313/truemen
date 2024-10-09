package com.truemen.api.post.query;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BasePageQuery {
    @NotNull
    @Min(0)
    protected Long page;
    @NotNull
    @Min(1)
    protected Long limit;

}
