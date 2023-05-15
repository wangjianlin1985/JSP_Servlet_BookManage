<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	//为了在翻页过程中保存查询条件
	$(function(){
		$("a").each(function(){
			this.onclick = function(){				
				var serializeVal = $(":hidden").serialize();
				var href = this.href + "&" + serializeVal;
				window.location.href = href;
				return false;
			}
		});
	})
</script>
<input type="hidden" name="searchWay" value="${param.searchWay }" />
<input type="hidden" name="searchValue" value="${param.searchValue }" />