package com.example.abw.servicies.pagination.car_ad.with_three_param;

import com.example.abw.entities.ad.Ad;
import com.example.abw.servicies.pagination.car_ad.SortKind;

import java.util.List;

public interface CarAdPagServiceWithThreeParam<T, T1, T2> {
    public List<Ad> getPaginationResult(T param, T1 param1, T2 param2, int page, int size,
                                        String filter, SortKind sortKind);

    public List<Ad> getPaginationResultByDefault(T param, T1 param1, T2 param2, int page,
                                                 String filter, SortKind sortKind);
}
