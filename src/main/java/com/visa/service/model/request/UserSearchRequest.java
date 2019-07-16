package com.visa.service.model.request;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.visa.service.model.constant.Status;
import com.visa.service.model.entity.QUser;
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
public class UserSearchRequest extends PaginatedSearchRequest {

  private final static String CREATED_AT = "createdAt";
  private final static String UPDATED_AT = "updatedAt";
  private final static String ASC = "asc";
  private final static String DESC = "desc";
  private Long toCreatedAt;
  private Long fromCreatedAt;
  private Long toUpdatedAt;
  private Long fromUpdatedAt;
  private String sortBy;
  private String name;
  private String lastName;
  private String email;
  private Status status;
  private String lastLoginDate;
  private String sortDirection;
  private String fullText;
  private int pageNumber;
  private int pageSize;

  @Override
  public BooleanExpression getBooleanExpression() {
    QUser user = QUser.user;
    List<BooleanExpression> booleanExpressions = new ArrayList<>(2);
    matchName(user, booleanExpressions);
    matchEmail(user, booleanExpressions);
    matchStatus(user, booleanExpressions);
    matchFullText(user, booleanExpressions);
    matchToCreatedAt(user, booleanExpressions);
    matchFromCreatedAt(user, booleanExpressions);
    matchToUpdatedAt(user, booleanExpressions);
    matchFromUpdatedAt(user, booleanExpressions);
    return mergeMatchers(booleanExpressions);
  }

  @Override
  protected QSort computeSort() {
    QUser user = QUser.user;
    QSort sort = new QSort(user.createdAt.desc());
    if (sortBy == null) {
      return sort;
    }
    if (sortBy.equals(UPDATED_AT)) {
      return sortByUpdatedAt(user);
    }
    if (sortBy.equals(CREATED_AT)) {
      return sortByCreatedAt(user);
    }
    return sort;
  }

  private QSort sortByUpdatedAt(QUser user) {
    if ((sortDirection != null) && sortDirection.toLowerCase().equals(ASC)) {
      return new QSort(user.updatedAt.asc());
    } else {
      return new QSort(user.updatedAt.desc());
    }
  }

  private QSort sortByCreatedAt(QUser user) {
    if ((sortDirection != null) && sortDirection.toLowerCase().equals(ASC)) {
      return new QSort(user.createdAt.asc());
    } else {
      return new QSort(user.createdAt.desc());
    }
  }

  private void matchStatus(QUser user, List<BooleanExpression> expressions) {
    if (status != null) {
      expressions.add(user.status.eq(status));
    }
  }

  private void matchName(QUser user, List<BooleanExpression> expressions) {
    if (name != null) {
      expressions.add(user.name.eq(name));
    }
  }

  private void matchEmail(QUser user, List<BooleanExpression> expressions) {
    if (email != null) {
      expressions.add(user.email.eq(email));
    }
  }

  private void matchToCreatedAt(QUser user, List<BooleanExpression> expressions) {
    if (toCreatedAt != null) {
      expressions.add(user.createdAt.before(new Timestamp(toCreatedAt)));
    }
  }

  private void matchFromCreatedAt(QUser user, List<BooleanExpression> expressions) {
    if (fromCreatedAt != null) {
      expressions.add(user.createdAt.after(new Timestamp(fromCreatedAt)));
    }
  }

  private void matchToUpdatedAt(QUser user, List<BooleanExpression> expressions) {
    if (toUpdatedAt != null) {
      expressions.add(user.updatedAt.before(new Timestamp(toUpdatedAt)));
    }
  }

  private void matchFromUpdatedAt(QUser user, List<BooleanExpression> expressions) {
    if (fromUpdatedAt != null) {
      expressions.add(user.updatedAt.after(new Timestamp(fromUpdatedAt)));
    }
  }

  private void matchFullText(QUser user, List<BooleanExpression> expressions) {
    if (!StringUtils.isEmpty(fullText)) {
      expressions.add(
          user.name
              .containsIgnoreCase(fullText)
              .or(user.uniqueKey.containsIgnoreCase(fullText))
      );
    }
  }
}
