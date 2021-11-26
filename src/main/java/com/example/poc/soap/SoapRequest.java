package com.example.poc.soap;

import com.example.poc.wsdl_classes.CapitalCity;
import com.example.poc.wsdl_classes.CapitalCityResponse;
import com.example.poc.wsdl_classes.CountryName;
import com.example.poc.wsdl_classes.CountryNameResponse;
import com.example.poc.wsdl_classes.LanguageName;
import com.example.poc.wsdl_classes.LanguageNameResponse;
import com.example.poc.wsdl_classes.ListOfContinentsByName;
import com.example.poc.wsdl_classes.ListOfContinentsByNameResponse;
import com.example.poc.wsdl_classes.ListOfCountryNamesByName;
import com.example.poc.wsdl_classes.ListOfCountryNamesByNameResponse;
import com.example.poc.wsdl_classes.ListOfCurrenciesByCode;
import com.example.poc.wsdl_classes.ListOfCurrenciesByCodeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.transport.http.ClientHttpRequestMessageSender;


@Component
public class SoapRequest extends WebServiceGatewaySupport {
    @Value("${timeout.connection-timeout}")
    private int connectionTimeout;

    @Value("${timeout.read-timeout}")
    private int readTimeout;

    public CapitalCityResponse capitalCitySoapResponse(CapitalCity capitalCity){
        CapitalCityResponse capitalCityResponse = new CapitalCityResponse();
        ConfigTimeout();
        ConfigCountryService();
        capitalCityResponse.setCapitalCityResult(((CapitalCityResponse) getWebServiceTemplate().marshalSendAndReceive(capitalCity)).getCapitalCityResult());
        return capitalCityResponse;
    }

    public CountryNameResponse countryNameSoapResponse(CountryName countryName) {
        CountryNameResponse countryNameResponse = new CountryNameResponse();
        ConfigTimeout();
        ConfigCountryService();
        countryNameResponse.setCountryNameResult(((CountryNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(countryName))
                .getCountryNameResult());
        return countryNameResponse;
    }

    public LanguageNameResponse languageNameSoapResponse(LanguageName languageName) {
        LanguageNameResponse languageNameResponse = new LanguageNameResponse();
        ConfigTimeout();
        ConfigCountryService();
        languageNameResponse.setLanguageNameResult(((LanguageNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(languageName))
                .getLanguageNameResult());
        return languageNameResponse;
    }

    public ListOfContinentsByNameResponse listOfContinentsSoapResponse(ListOfContinentsByName listOfContinents) {
        ListOfContinentsByNameResponse list = new ListOfContinentsByNameResponse();
        ConfigTimeout();
        ConfigCountryService();
        list.setListOfContinentsByNameResult(((ListOfContinentsByNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(listOfContinents)).getListOfContinentsByNameResult());
        return list;
    }

    public ListOfCountryNamesByNameResponse listOfCountriesSoapResponse(ListOfCountryNamesByName listOfCountryNames) {
        ListOfCountryNamesByNameResponse list = new ListOfCountryNamesByNameResponse();
        ConfigTimeout();
        ConfigCountryService();
        list.setListOfCountryNamesByNameResult(((ListOfCountryNamesByNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(listOfCountryNames)).etListOfCountryNamesByNameResult());
        return list;
    }

    public ListOfCurrenciesByCodeResponse listOfCurrenciesSoapResponse(ListOfCurrenciesByCode listOfCurrencies) {
        ListOfCurrenciesByCodeResponse list = new ListOfCurrenciesByCodeResponse();
        ConfigTimeout();
        ConfigCountryService();
        list.setListOfCurrenciesByCodeResult(((ListOfCurrenciesByCodeResponse) getWebServiceTemplate()
                .marshalSendAndReceive(listOfCurrencies)).getListOfCurrenciesByCodeResult());
        return list;
    }

    private void ConfigCountryService() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.poc.wsdl_classes");

        this.setDefaultUri("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso");
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    private void ConfigTimeout() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectionTimeout);
        requestFactory.setReadTimeout(readTimeout);
        setMessageSender(new ClientHttpRequestMessageSender(requestFactory));
    }
}
