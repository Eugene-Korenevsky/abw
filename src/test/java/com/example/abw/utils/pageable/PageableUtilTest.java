package com.example.abw.utils.pageable;

import com.example.abw.exception.validation.ValidationException;
import com.example.abw.model.currency.Currency;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.model.pageable.SortKind;
import com.example.abw.utils.PageableUtil;
import org.junit.Test;
import org.springframework.data.domain.Pageable;

import static org.junit.Assert.assertEquals;

public class PageableUtilTest {


    @Test
    public void testUtil() throws ValidationException {
        PageableParams pageableParams = new PageableParams();
        pageableParams.setPage(1);
        pageableParams.setCurrency(Currency.BYN);
        pageableParams.setSize(10);
        pageableParams.setSortKind(SortKind.ASC);
        pageableParams.setFilter("price");
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        assertEquals(10,pageable.getPageSize());
        assertEquals(1,pageable.getPageNumber());
    }
}
