// Tree
var setTree = function (strTreeClass, fncToggleClick) {
	var tree = $(strTreeClass);
	var togglePlus  = '\<button type=\"button\" class=\"toggle plus\"\>+\<\/button\>';
	var toggleMinus = '\<button type=\"button\" class=\"toggle minus\"\>-\<\/button\>';
	
	// default
	tree.find('ul>li:last-child').addClass('last');
	tree.find('li>ul:hidden').parent('li').prepend(togglePlus);
	tree.find('li>ul:visible').parent('li').prepend(toggleMinus);
	
	// active
	tree.find('li.active').parents('li').addClass('open');
	tree.find('li.open').parents('li').addClass('open');
	tree.find('li.open>.toggle').text('-').removeClass('plus').addClass('minus');
	tree.find('li.open>ul').slideDown(100);
};

/** LNB 토글 클릭 이벤트 */
var fncLnbChilds= function (objThis) {
	var objParentLi = objThis.parent();
	objParentLi.toggleClass('open');

	if(objParentLi.hasClass('open')){
		objParentLi.find(">ul").slideDown(100);
		objThis.text('-').removeClass('plus').addClass('minus');
	} else {
		objThis.text('+').removeClass('minus').addClass('plus');
		objParentLi.find('>ul').slideUp(100);
		uiRefresh();
	}
};

/** LNB 자동 열기 */
var fncOpenSelfTree = function (strSelectedMenuId) {
	
	var objSelectedTitle = $(".menu_tree a[title='" + strSelectedMenuId + "']");
	var objParent = objSelectedTitle.parent("li").parent("ul").parent("li").find(".toggle:first");
	if (objParent.length > 0) {
		objParent.trigger("click");
		fncOpenSelfTree(objParent.next("a:first").attr("title"));
	}
}

$(document).ready(function () {
	
	var strSelectedMenuId = $("#divSelectedMenuId").text();
	
	// LNB Tree Init
	setTree(".menu_tree");
	
	// LNB Toggle Click Function
	$(".menu_tree button.toggle").bind("click", function () {
		fncLnbChilds($(this));
		uiRefresh();
	});
	
	// LNB 링크가 #인 아이들은 토글 실행
	$(".menu_tree a[href*='#']").click(function () {
		$(this).parent().find(".toggle:first").trigger("click");
		return false;
	});
	// LNB Init
	if (strSelectedMenuId.length > 0) {
		fncOpenSelfTree(strSelectedMenuId);
	}
});

