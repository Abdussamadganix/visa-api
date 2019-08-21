package com.visa.service.model.request;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.visa.service.model.entity.QMerchantAlias;
import com.visa.service.model.entity.QUser;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.querydsl.QSort;

/**
 * @author Abdussamad
 */
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MerchantSearchRequest extends PaginatedSearchRequest {

  private final static String CREATED_AT = "createdAt";
  private final static String UPDATED_AT = "updatedAt";
  private final static String ASC = "asc";
  private final static String DESC = "desc";
  private String sortBy;
  private String sortDirection;


  @Override
  public BooleanExpression getBooleanExpression() {
    QMerchantAlias merchantAlias = QMerchantAlias.merchantAlias;
    List<BooleanExpression> booleanExpressions = new ArrayList<>(2);
    return mergeMatchers(booleanExpressions);
  }

  @Override
  protected QSort computeSort() {
    QMerchantAlias merchantAlias = QMerchantAlias.merchantAlias;
    QSort sort = new QSort(merchantAlias.createdAt.desc());
    if (sortBy == null) {
      return sort;
    }
    if (sortBy.equals(UPDATED_AT)) {
      return sortByUpdatedAt(merchantAlias);
    }
    if (sortBy.equals(CREATED_AT)) {
      return sortByCreatedAt(merchantAlias);
    }
    return sort;
  }

  private QSort sortByUpdatedAt(QMerchantAlias merchantAlias) {
    if ((sortDirection != null) && sortDirection.toLowerCase().equals(ASC)) {
      return new QSort(merchantAlias.updatedAt.asc());
    } else {
      return new QSort(merchantAlias.updatedAt.desc());
    }
  }

  private QSort sortByCreatedAt(QMerchantAlias merchantAlias) {
    if ((sortDirection != null) && sortDirection.toLowerCase().equals(ASC)) {
      return new QSort(merchantAlias.createdAt.asc());
    } else {
      return new QSort(merchantAlias.createdAt.desc());
    }
  }

}
