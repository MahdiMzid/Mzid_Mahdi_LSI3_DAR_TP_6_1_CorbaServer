package service;

import corbaConversion.IConversionRemotePOA;

public class ConversionImpl extends IConversionRemotePOA {

    @Override
    public double conversionMontant(float mt) {
        return mt*3.3;
    }
}
