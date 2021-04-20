package com.example.abw.utils.pageable_params;

import com.example.abw.model.pageable.PageableParams;

public interface PageableParamsUtil {
    public PageableParams getPageableParams(Integer page, Integer size, String sortBy, String sortKind);
}
