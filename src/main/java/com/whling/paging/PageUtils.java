package com.whling.paging;

import java.util.List;
import java.util.function.Function;

public class PageUtils {

    public static <T, R> Page<R> mapToBatchly(Page<T> originPage, Function<List<T>, List<R>> listMapper) {
        PageInfo pageInfo = originPage.getPageInfo();
        Page<R> result = new Page<>(pageInfo.getTotalItem(),
                pageInfo.getCurrentPage(), pageInfo.getPageSize());
        result.setData(listMapper.apply(originPage.getData()));
        return result;
    }
}
