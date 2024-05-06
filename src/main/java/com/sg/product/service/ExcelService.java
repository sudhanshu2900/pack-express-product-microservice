package com.sg.product.service;

import java.io.ByteArrayInputStream;

import java.io.IOException;

public interface ExcelService {

    public ByteArrayInputStream getAllProductsInExcelFormat() throws IOException;

    public ByteArrayInputStream getAllRejectedProductsInExcelFormat() throws IOException;

    public ByteArrayInputStream getInTransitProductsInExcelFormat() throws IOException;

    public ByteArrayInputStream getAllDeliveredProductsInExcelFormat() throws IOException;

    public ByteArrayInputStream getAllProductsWithCustIdInExcelFormat(int id) throws IOException;

}
