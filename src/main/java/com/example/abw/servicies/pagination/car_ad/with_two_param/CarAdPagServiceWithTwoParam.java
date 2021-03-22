package com.example.abw.servicies.pagination.car_ad.with_two_param;

import com.example.abw.entities.ad.Ad;
import com.example.abw.servicies.pagination.car_ad.SortKind;

import java.util.List;

public interface CarAdPagServiceWithTwoParam<T, T1> {
    public List<Ad> getPaginationResult(T param, T1 param1, int page, int size,
                                        String filter, SortKind sortKind, boolean isAdmin);

    public List<Ad> getPaginationResultByDefault(T param, T1 param1, int page,
                                                 String filter, SortKind sortKind, boolean isAdmin);

}
