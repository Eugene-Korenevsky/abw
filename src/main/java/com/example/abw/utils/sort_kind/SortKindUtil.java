package com.example.abw.utils.sort_kind;

import com.example.abw.model.pageable.sort_kind.SortKind;
import org.springframework.stereotype.Service;

@Service
public class SortKindUtil {
    public SortKind getSortKind(String type) {
        if (type == null || type.equals("desc")) return SortKind.DESC;
        return SortKind.ASC;
    }
}
