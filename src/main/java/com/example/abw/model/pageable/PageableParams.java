package com.example.abw.model.pageable;

import com.example.abw.model.pageable.sort_kind.SortKind;
import lombok.Data;

@Data
public class PageableParams {
    private int page;
    private int size;
    private String filter;
    private SortKind sortKind;
}
