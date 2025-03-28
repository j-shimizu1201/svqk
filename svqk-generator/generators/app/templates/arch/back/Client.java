<% include('../../lib/interface-common'); -%>
<%_
idMethodArgs = ifcom.buildArgs((field) => `${field.javaType} ${field.fieldName}`);
givenGetArgs = ifcom.buildArgs((field) => field.fieldName);
-%>
package <%= interfacesPkgNm %>;

import static <%= interfacesPkgNm %>.<%= entityNmPascal %>Controller.*;
import static <%= interfacesPkgNm %>.<%= entityNmPascal %>Controller.<%= entityNmPascal %>SearchResultDto;
import dev.aulait.svqk.arch.exception.ErrorResponseDto;
import dev.aulait.svqk.arch.test.RestClientUtils;

public class <%= entityNmPascal %>Client {

  private static final String <%= entityNmAllCaps %>_AND_<%= entityNmAllCaps %>_ID_PATH = <%= entityNmAllCaps %>_PATH + "/" + <%= entityNmAllCaps %>_ID_PATH;

  public <%= entityNmPascal %>Dto get(<%= idMethodArgs %>) {
    return RestClientUtils.get(<%= entityNmAllCaps %>_AND_<%= entityNmAllCaps %>_ID_PATH, <%= entityNmPascal %>Dto.class, <%= givenGetArgs %>);
  }

  public ErrorResponseDto getWithError(<%= idMethodArgs %>) {
    return RestClientUtils.getWithError(<%= entityNmAllCaps %>_AND_<%= entityNmAllCaps %>_ID_PATH, <%= givenGetArgs %>);
  }

  public <%= ifcom.interfaceIdType %> save(<%= entityNmPascal %>Dto dto) {
    return RestClientUtils.post(<%= entityNmAllCaps %>_PATH, dto, <%= ifcom.interfaceIdType %>.class);
  }

  public <%= ifcom.interfaceIdType %> update(<%= entityNmPascal %>Dto dto) {
    return RestClientUtils.put(<%= entityNmAllCaps %>_PATH, dto, <%= ifcom.interfaceIdType %>.class);
  }

  public <%= ifcom.interfaceIdType %> delete(<%= idMethodArgs %>, <%= entityNmPascal %>Dto <%= entityNmCamel %>) {
    return RestClientUtils.delete(<%= entityNmAllCaps %>_AND_<%= entityNmAllCaps %>_ID_PATH, <%= entityNmCamel %>, <%= ifcom.interfaceIdType %>.class, <%= givenGetArgs %>);
  }

  public <%= entityNmPascal %>SearchResultDto search(<%= entityNmPascal %>SearchCriteriaDto dto) {
    return RestClientUtils.post(<%= entityNmAllCaps %>_PATH + "/" + <%= entityNmAllCaps %>_SEARCH_PATH, dto, <%= entityNmPascal %>SearchResultDto.class);
  }
}