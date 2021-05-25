package com.example.abw.model.pageable;

import com.example.abw.model.currency.Currency;
import com.example.abw.model.pageable.sort_kind.SortKind;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
public class PageableParams {
    @NonNull
    private int page;
    @NonNull
    private int size;
    @NotNull
    private String filter;
    @NotNull
    private SortKind sortKind;
    @NotNull
    private Currency currency;
}
