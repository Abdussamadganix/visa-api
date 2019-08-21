package com.visa.service.model.request;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.visa.service.model.entity.QPayment;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.querydsl.QSort;
import org.springframework.util.StringUtils;

/**
 * @author Abdussamad
 */
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PaymentSearchRequest extends PaginatedSearchRequest {

  private final static String AMOUNT = "amount";
  private final static String CREATED_AT = "createdAt";
  private final static String UPDATED_AT = "updatedAt";
  private final static String ASC = "asc";
  private final static String DESC = "desc";
  private Long toCreatedAt;
  private Long fromCreatedAt;
  private Long toUpdatedAt;
  private Long fromUpdatedAt;
  private Long toAmount;
  private Long fromAmount;
  private String sortBy;
  private String sortDirection;
  private String uniqueKey;
  private String transactionId;
  private String fullText;
  private int pageNumber;
  private int pageSize;
  private String merchant;
  private String publicKey;
  private String paymentResponseCode;
  private List<String> paymentResponseCodes;
  private boolean all;
  private String isReportPushed;
  private List<String> isReportPusheds;


  @Override
  public BooleanExpression getBooleanExpression() {
    QPayment payment = QPayment.payment;
    List<BooleanExpression> booleanExpressions = new ArrayList<>(2);
    matchUniqueKey(payment, booleanExpressions);
    matchFullText(payment, booleanExpressions);
    matchToCreatedAt(payment, booleanExpressions);
    matchFromCreatedAt(payment, booleanExpressions);
    matchToUpdatedAt(payment, booleanExpressions);
    matchFromUpdatedAt(payment, booleanExpressions);
    return mergeMatchers(booleanExpressions);
  }

  @Override
  protected QSort computeSort() {
    QPayment payment = QPayment.payment;
    QSort sort = new QSort(payment.createdAt.desc());
    if (sortBy == null) {
      return sort;
    }
    if (sortBy.equals(AMOUNT)) {
      return sortByAmount(payment);
    }
    if (sortBy.equals(UPDATED_AT)) {
      return sortByUpdatedAt(payment);
    }
    if (sortBy.equals(CREATED_AT)) {
      return sortByCreatedAt(payment);
    }
    return sort;
  }

  private QSort sortByAmount(QPayment payment) {
    if ((sortDirection != null) && sortDirection.toLowerCase().equals(ASC)) {
      return new QSort(payment.amount.asc());
    } else {
      return new QSort(payment.amount.desc());
    }
  }

  private QSort sortByUpdatedAt(QPayment payment) {
    if ((sortDirection != null) && sortDirection.toLowerCase().equals(ASC)) {
      return new QSort(payment.updatedAt.asc());
    } else {
      return new QSort(payment.updatedAt.desc());
    }
  }

  private QSort sortByCreatedAt(QPayment payment) {
    if ((sortDirection != null) && sortDirection.toLowerCase().equals(ASC)) {
      return new QSort(payment.createdAt.asc());
    } else {
      return new QSort(payment.createdAt.desc());
    }
  }


  private void matchUniqueKey(QPayment payment, List<BooleanExpression> expressions) {
    if (uniqueKey != null) {
      expressions.add(payment.uniqueKey.eq(uniqueKey));
    }
  }


  private void matchToCreatedAt(QPayment payment, List<BooleanExpression> expressions) {
    if (toCreatedAt != null) {
      expressions.add(payment.createdAt.before(new Timestamp(toCreatedAt)));
    }
  }

  private void matchFromCreatedAt(QPayment payment, List<BooleanExpression> expressions) {
    if (fromCreatedAt != null) {
      expressions.add(payment.createdAt.after(new Timestamp(fromCreatedAt)));
    }
  }

  private void matchToUpdatedAt(QPayment payment, List<BooleanExpression> expressions) {
    if (toUpdatedAt != null) {
      expressions.add(payment.updatedAt.before(new Timestamp(toUpdatedAt)));
    }
  }

  private void matchFromUpdatedAt(QPayment payment, List<BooleanExpression> expressions) {
    if (fromUpdatedAt != null) {
      expressions.add(payment.updatedAt.after(new Timestamp(fromUpdatedAt)));
    }
  }


  private void matchFullText(QPayment payment, List<BooleanExpression> expressions) {
    if (!StringUtils.isEmpty(fullText)) {
      expressions.add(
          payment.uniqueKey.containsIgnoreCase(fullText)
      );
    }
  }


}
