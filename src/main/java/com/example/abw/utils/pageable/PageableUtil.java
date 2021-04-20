package com.example.abw.utils.pageable;

import com.example.abw.model.pageable.PageableParams;
import com.example.abw.model.pageable.sort_kind.SortKind;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class PageableUtil {

    public static Pageable getPageable(PageableParams pageableParams) {
        Pageable pageable;
        if (pageableParams.getSortKind() == SortKind.ASC) {
            pageable = PageRequest.of(pageableParams.getPage(),
                    pageableParams.getSize(), Sort.by(pageableParams.getFilter()).ascending());
        } else {
            pageable = PageRequest.of(pageableParams.getPage(),
                    pageableParams.getSize(), Sort.by(pageableParams.getFilter()).descending());
        }
        return pageable;
    }
}
