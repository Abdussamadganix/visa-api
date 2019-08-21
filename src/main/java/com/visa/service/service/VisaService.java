package com.visa.service.service;

import com.visa.service.exception.BadRequestException;
import com.visa.service.model.constant.Status;
import com.visa.service.model.entity.MerchantAlias;
import com.visa.service.model.entity.User;
import com.visa.service.model.request.ApiCreateAliasRequest;
import com.visa.service.model.request.ApiCreateMerchantAliasRequest;
import com.visa.service.model.request.ApiDeleteAliasRequest;
import com.visa.service.model.request.ApiGetAliasRequest;
import com.visa.service.model.request.ApiResolveAliasRequest;
import com.visa.service.model.request.ApiUpdateAliasRequest;
import com.visa.service.model.request.ApiUpdateMerchantAliasRequest;
import com.visa.service.model.request.UserSearchRequest;
import com.visa.service.model.response.ApiCreateAliasResponse;
import com.visa.service.model.response.ApiDeleteAliasResponse;
import com.visa.service.model.response.ApiResolveAliasResponse;
import com.visa.service.model.response.FailedResponse;
import com.visa.service.model.response.MerchantAliasResponse;
import com.visa.service.model.response.SuccessResponse;
import com.visa.service.util.SecurityUtil;
import com.visa.service.visa.configuration.VisaConfiguration;
import com.visa.service.visa.model.request.CreateAliasRequest;
import com.visa.service.visa.model.request.CreateMerchantAliasRequest;
import com.visa.service.visa.model.request.DeleteAliasRequest;
import com.visa.service.visa.model.request.DeleteMerchantAliasRequest;
import com.visa.service.visa.model.request.GetAliasRequest;
import com.visa.service.visa.model.request.GetMerchantAliasRequest;
import com.visa.service.visa.model.request.ReceiveMerchantPayment;
import com.visa.service.visa.model.request.ResolveAliasRequest;
import com.visa.service.visa.model.request.UpdateAliasRequest;
import com.visa.service.visa.model.request.UpdateMerchantAliasRequest;
import com.visa.service.visa.model.response.CreateAliasResponse;
import com.visa.service.visa.model.response.CreateMerchantAliasResponse;
import com.visa.service.visa.model.response.DeleteAliasResponse;
import com.visa.service.visa.model.response.DeleteMerchantAliasResponse;
import com.visa.service.visa.model.response.GetAliasResponse;
import com.visa.service.visa.model.response.GetMerchantAliasResponse;
import com.visa.service.visa.model.response.ResolveAliasResponse;
import com.visa.service.visa.model.response.ResponseStatus;
import com.visa.service.visa.model.response.UpdateMerchantAliasResponse;
import com.visa.service.visa.service.ApiClient;
import com.visa.service.visa.service.VisaHttpClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Abdussamad
 */
@Service
public class VisaService {

  private final VisaConfiguration configuration;
  private final VisaHttpClient visaHttpClient;
  private final ApiClient apiClient;
  private final MerchantAliasService merchantAliasService;
  private final String ALIAS_RESOLVE_PATH = "/visaaliasdirectory/v1/resolve";
  private final String CREATE_ALIAS_PATH = "/visaaliasdirectory/v1/manage/createalias";
  private final String UPDATE_ALIAS_PATH = "/visaaliasdirectory/v1/manage/updatealias";
  private final String CREATE_MERCHANT_ALIAS__PATH = "/visaaliasdirectory/v1/managemerchant/createalias";
  private final String UPDATE_MERCHANT_ALIAS_PATH = "/visaaliasdirectory/v1/managemerchant/updatealias";
  private final String DELETE_MERCHANT_ALIAS_PATH = "/visaaliasdirectory/v1/managemerchant/deletealias";
  private final String GET_MERCHANT_ALIAS_PATH = "/visaaliasdirectory/v1/managemerchant";

  private final Logger LOGGER = LoggerFactory.getLogger(VisaService.class);
  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(VisaService.class);

  @Autowired
  public VisaService(VisaConfiguration configuration, VisaHttpClient visaHttpClient,
      ApiClient apiClient, MerchantAliasService merchantAliasService) {
    Assert.notNull(configuration);
    Assert.notNull(visaHttpClient);
    Assert.notNull(apiClient);
    Assert.notNull(merchantAliasService);
    this.configuration = configuration;
    this.visaHttpClient = visaHttpClient;
    this.apiClient = apiClient;
    this.merchantAliasService = merchantAliasService;
  }

  public SuccessResponse resolveAlias(ApiResolveAliasRequest apiResolveAliasRequest) {
    ResolveAliasRequest resolveAliasRequest = apiResolveAliasRequest.toResolveAliasRequest();
    ParameterizedTypeReference<ResolveAliasResponse> returnType = new ParameterizedTypeReference<ResolveAliasResponse>() {
    };
    ResolveAliasResponse response = apiClient
        .invokeAPI(ALIAS_RESOLVE_PATH, HttpMethod.POST, resolveAliasRequest, returnType,
            ResolveAliasResponse.class);
//    ResolveAliasResponse response = apiClient.invokeAPI(configuration.getBaseUrl() + ALIAS_RESOLVE_PATH, configuration.getBaseUrl() + ALIAS_RESOLVE_PATH, HttpMethod.POST, resolveAliasRequest, ResolveAliasResponse.class, getHeaders());
    return createResponseDataForResolveAlias(response);
  }

  public SuccessResponse createAlias(ApiCreateAliasRequest request) {
    CreateAliasRequest createAliasRequest = request.toCreateAliasRequest();
//    ParameterizedTypeReference<CreateAliasResponse> returnType = new ParameterizedTypeReference<CreateAliasResponse>() {};
//    CreateAliasResponse response = apiClient.invokeAPI(ALIAS_RESOLVE_PATH, HttpMethod.POST, createAliasRequest, returnType, CreateAliasResponse.class);
    CreateAliasResponse response = visaHttpClient
        .post(configuration.getBaseUrl() + CREATE_ALIAS_PATH, createAliasRequest,
            CreateAliasResponse.class, getHeaders());
    return createResponseDataForCreateAlias(response);
  }

  public SuccessResponse updateAlias(ApiUpdateAliasRequest request) {
    UpdateAliasRequest updateAliasRequest = request.toUpdateAliasRequest();
    CreateAliasResponse response = visaHttpClient
        .post(configuration.getBaseUrl() + UPDATE_ALIAS_PATH, updateAliasRequest,
            CreateAliasResponse.class, getHeaders());
    return createResponseDataForCreateAlias(response);
  }

  public SuccessResponse deleteAlias(ApiDeleteAliasRequest request) {
    DeleteAliasRequest deleteAliasRequest = request.toDeleteAliasRequest();
    DeleteAliasResponse response = visaHttpClient
        .post(configuration.getBaseUrl() + UPDATE_ALIAS_PATH, deleteAliasRequest,
            DeleteAliasResponse.class, getHeaders());
    return createResponseDataForDeleteAlias(response);
  }

  public SuccessResponse getAlias(ApiGetAliasRequest apiGetAliasRequest) {
    GetAliasRequest getAliasRequest = apiGetAliasRequest.toGetAliasRequest();
    GetAliasResponse response = visaHttpClient
        .post(configuration.getBaseUrl() + UPDATE_ALIAS_PATH, getAliasRequest,
            GetAliasResponse.class, getHeaders());
    return createResponseDataForGetAlias(response);
  }

  public SuccessResponse createMerchantAlias(
      ApiCreateMerchantAliasRequest apiCreateMerchantAliasRequest) {
    CreateMerchantAliasRequest createMerchantAliasRequest = apiCreateMerchantAliasRequest
        .toCreateMerchantAliasRequest();
    MerchantAlias merchantAlias = merchantAliasService
        .createMerchantAlias(apiCreateMerchantAliasRequest.toMerchantAlias());
    CreateMerchantAliasResponse response = visaHttpClient
        .post(configuration.getBaseUrl() + CREATE_MERCHANT_ALIAS__PATH, createMerchantAliasRequest,
            CreateMerchantAliasResponse.class, getHeaders());
    if (response.getResponseStatus() != null || response.getMessage() != null) {
      merchantAlias.setCreateStatus(Status.FAILED);
      merchantAlias.setErrorReason((response.getMessage() != null) ? response.getMessage()
          : response.getResponseStatus().getMessage());
    } else {
      merchantAlias.setCreateStatus(Status.SUCCESSFUL);
    }
    merchantAliasService.updateMerchantAlias(merchantAlias, merchantAlias);
    return createResponseDataForCreateMerchantAliasResponse(response);
  }

  public SuccessResponse updateMerchantAlias(
      ApiUpdateMerchantAliasRequest apiUpdateMerchantAliasRequest) {
    UpdateMerchantAliasRequest updateMerchantAliasRequest = apiUpdateMerchantAliasRequest
        .toUpdateMerchantAliasRequest();
    UpdateMerchantAliasResponse response = visaHttpClient
        .post(configuration.getBaseUrl() + UPDATE_MERCHANT_ALIAS_PATH, updateMerchantAliasRequest,
            UpdateMerchantAliasResponse.class, getHeaders());
    MerchantAlias merchantAliasToUpdate = merchantAliasService
        .fetchByAliasId(apiUpdateMerchantAliasRequest.getAliasId());
    MerchantAlias updateMerchantAlias = apiUpdateMerchantAliasRequest.toMerchantAlias();
    merchantAliasService.updateMerchantAlias(merchantAliasToUpdate, updateMerchantAlias);
    return createResponseDataForUpdateMerchantAliasResponse(response);
  }

  public SuccessResponse deleteMerchantAlias(
      DeleteMerchantAliasRequest deleteMerchantAliasRequest) {
    DeleteMerchantAliasResponse response = visaHttpClient
        .post(configuration.getBaseUrl() + DELETE_MERCHANT_ALIAS_PATH, deleteMerchantAliasRequest,
            DeleteMerchantAliasResponse.class, getHeaders());
    return createResponseDataForDeleteMerchantAliasResponse(response);
  }

  public SuccessResponse getMerchantAlias(GetMerchantAliasRequest getMerchantAliasRequest) {
    GetMerchantAliasResponse response = visaHttpClient
        .get(configuration.getBaseUrl() + GET_MERCHANT_ALIAS_PATH
                + "?merchantaliasid=" + getMerchantAliasRequest.getMerchantAliasId()
                + ((getMerchantAliasRequest.getType() != null) ? "&type=" + getMerchantAliasRequest
                .getType() : ""),
            GetMerchantAliasResponse.class, getHeaders());
    return createResponseDataForGetMerchantAliasResponse(response);
  }


  public void updateTransactionUsingCallbackNotitification(
      ReceiveMerchantPayment receiveMerchantPayment) {

  }

  private SuccessResponse createResponseDataForResolveAlias(ResolveAliasResponse response) {
    Map<String, Object> data = new HashMap<>();
    if (response.getResponseStatus() != null) {
      data.put("resolveAlias", (response.getResponseStatus() != null)
          ? FailedResponse.fromResponseStatus(response.getResponseStatus().getMessage(),
          response.getResponseStatus().getReason())
          : FailedResponse.fromResponseStatus(response.getMessage(), response.getReason()));
      return buildFailedResponse(data);
    } else {
      data.put("resolveAlias", ApiResolveAliasResponse.formResolveAliasResponse(response));
    }
    return buildSuccessResponse(data);
  }

  private SuccessResponse createResponseDataForCreateAlias(CreateAliasResponse response) {
    Map<String, Object> data = new HashMap<>();
    if (response.getResponseStatus() != null || response.getMessage() != null) {
      data.put("alias", (response.getResponseStatus() != null)
          ? FailedResponse.fromResponseStatus(response.getResponseStatus().getMessage(),
          response.getResponseStatus().getReason())
          : FailedResponse.fromResponseStatus(response.getMessage(), response.getReason()));
      return buildFailedResponse(data);
    }
    data.put("alias", ApiCreateAliasResponse.fromCreateAliasResponse(response));
    return buildSuccessResponse(data);
  }

  private SuccessResponse createResponseDataForDeleteAlias(DeleteAliasResponse response) {
    Map<String, Object> data = new HashMap<>();
    if (response.getResponseStatus() != null || response.getMessage() != null) {
      data.put("alias", (response.getResponseStatus() != null)
          ? FailedResponse.fromResponseStatus(response.getResponseStatus().getMessage(),
          response.getResponseStatus().getReason())
          : FailedResponse.fromResponseStatus(response.getMessage(), response.getReason()));
      return buildFailedResponse(data);
    }
    data.put("alias", ApiDeleteAliasResponse.fromDeleteAliasResponse(response));
    return buildSuccessResponse(data);
  }

  private SuccessResponse createResponseDataForGetAlias(GetAliasResponse response) {
    Map<String, Object> data = new HashMap<>();
    if (response.getResponseStatus() != null || response.getMessage() != null) {
      data.put("alias", (response.getResponseStatus() != null)
          ? FailedResponse.fromResponseStatus(response.getResponseStatus().getMessage(),
          response.getResponseStatus().getReason())
          : FailedResponse.fromResponseStatus(response.getMessage(), response.getReason()));
      return buildFailedResponse(data);
    }
    data.put("alias", response);
    return buildSuccessResponse(data);
  }

  private SuccessResponse createResponseDataForCreateMerchantAliasResponse(
      CreateMerchantAliasResponse response) {
    Map<String, Object> data = new HashMap<>();
    if (response.getResponseStatus() != null || response.getMessage() != null) {
      data.put("alias", (response.getResponseStatus() != null)
          ? FailedResponse.fromResponseStatus(response.getResponseStatus().getMessage(),
          response.getResponseStatus().getReason())
          : FailedResponse.fromResponseStatus(response.getMessage(), response.getReason()));
      return buildFailedResponse(data);
    }
    data.put("merchantAlias", response);
    return buildSuccessResponse(data);
  }

  private SuccessResponse createResponseDataForUpdateMerchantAliasResponse(
      UpdateMerchantAliasResponse response) {
    Map<String, Object> data = new HashMap<>();
    if (response.getResponseStatus() != null || response.getMessage() != null) {
      data.put("alias", (response.getResponseStatus() != null)
          ? FailedResponse.fromResponseStatus(response.getResponseStatus().getMessage(),
          response.getResponseStatus().getReason())
          : FailedResponse.fromResponseStatus(response.getMessage(), response.getReason()));
      return buildFailedResponse(data);
    }
    data.put("merchantAlias", response);
    return buildSuccessResponse(data);
  }

  private SuccessResponse createResponseDataForDeleteMerchantAliasResponse(
      DeleteMerchantAliasResponse response) {
    Map<String, Object> data = new HashMap<>();
    if (response.getResponseStatus() != null || response.getMessage() != null) {
      data.put("alias", (response.getResponseStatus() != null)
          ? FailedResponse.fromResponseStatus(response.getResponseStatus().getMessage(),
          response.getResponseStatus().getReason())
          : FailedResponse.fromResponseStatus(response.getMessage(), response.getReason()));
      return buildFailedResponse(data);
    }
    data.put("merchantAlias", response);
    return buildSuccessResponse(data);
  }

  private SuccessResponse createResponseDataForGetMerchantAliasResponse(
      GetMerchantAliasResponse response) {
    Map<String, Object> data = new HashMap<>();
    if (response.getResponseStatus() != null || response.getMessage() != null) {
      data.put("alias", (response.getResponseStatus() != null)
          ? FailedResponse.fromResponseStatus(response.getResponseStatus().getMessage(),
          response.getResponseStatus().getReason())
          : FailedResponse.fromResponseStatus(response.getMessage(), response.getReason()));
      return buildFailedResponse(data);
    }
    data.put("merchantAlias", response);
    return buildSuccessResponse(data);
  }

  private SuccessResponse buildSuccessResponse(Map<String, Object> data) {
    return SuccessResponse.builder().status(Status.SUCCESSFUL).data(data).build();
  }

  private SuccessResponse buildFailedResponse(Map<String, Object> data) {
    return SuccessResponse.builder().status(Status.FAILED).data(data).build();
  }

  private Map<String, String> getHeaders() {
    Map<String, String> headers = new HashMap<>(1);
    headers.put("Authorization", "Basic " + SecurityUtil
        .base64Converter(configuration.getUsername() + ":" + configuration.getPassword()));
    return headers;
  }


}
