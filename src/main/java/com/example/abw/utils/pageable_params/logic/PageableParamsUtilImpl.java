package com.example.abw.utils.pageable_params.logic;

import com.example.abw.AppProperties;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.utils.sort_kind.SortKindUtil;
import com.example.abw.utils.pageable_params.PageableParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageableParamsUtilImpl implements PageableParamsUtil {
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private SortKindUtil sortKindUtil;

    @Override
    public PageableParams getPageableParams(Integer page, Integer size, String sortBy, String sortKind) {
        PageableParams pageableParams = new PageableParams();
        if (page == null) pageableParams.setPage(0);
        else pageableParams.setPage(page);
        if (size == null) pageableParams.setSize(appProperties.getPageSize());
        else pageableParams.setSize(size);
        if (sortBy == null) pageableParams.setFilter(appProperties.getDefaultFilter());
        else pageableParams.setFilter(sortBy);
        pageableParams.setSortKind(sortKindUtil.getSortKind(sortKind));
        return pageableParams;
    }
}
