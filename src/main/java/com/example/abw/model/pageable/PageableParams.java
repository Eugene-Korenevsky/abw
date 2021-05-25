package com.example.abw.model.pageable;

import com.example.abw.model.currency.Currency;
import com.example.abw.model.pageable.sort_kind.SortKind;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
public class PageableParams {
    @NotNull(message = "page number is required")
    private Integer page;
    @NotNull(message = "page size is required")
    private Integer size;
    @NotNull(message = "filter must not be null")
    private String filter;
    @NotNull(message = "sortKind is required")
    private SortKind sortKind;
    @NotNull(message = "currency is required")
    private Currency currency;
}
