/*         ______________________________________
  ________|                                      |_______
  \       |           SmartAdmin WebApp          |      /
   \      |      Copyright © 2014 MyOrange       |     /
   /      |______________________________________|     \
  /__________)                                (_________\

 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * =======================================================================
 * SmartAdmin is FULLY owned and LICENSED by MYORANGE INC.
 * This script may NOT be RESOLD or REDISTRUBUTED under any
 * circumstances, and is only to be used with this purchased
 * copy of SmartAdmin Template.
 * =======================================================================
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * =======================================================================
 * original filename: ng.app.js
 * author: Sunny (@bootstraphunt)
 * email: info@myorange.ca
 */

// APP DIRECTIVES
// main directives
angular.module('app.main', [])
	// initiate body
	.directive('body', function() {
		return {
			restrict: 'E',
			link: function(scope, element, attrs) {
				element.on('click', 'a[href="#"], [data-toggle]', function(e) {
					e.preventDefault();
				})
			}
		}
	})

	.factory('ribbon', ['$rootScope', function($rootScope) {
		var ribbon = {
			currentBreadcrumb: [],
			updateBreadcrumb: function(crumbs) {
				crumbs.push('Home');
				var breadCrumb = crumbs.reverse();
				ribbon.currentBreadcrumb = breadCrumb;
				$rootScope.$broadcast('navItemSelected', breadCrumb);
			}
		};

		return ribbon;
	}])

	.directive('action', function() {
		return {
			restrict: 'A',
			link: function(scope, element, attrs) {
				/*
				 * SMART ACTIONS
				 */
				var smartActions = {
	    
				    // LOGOUT MSG 
				    userLogout: function($this){
				
						// ask verification
						$.SmartMessageBox({
							title : "<i class='fa fa-sign-out txt-color-orangeDark'></i> 是否退出 <span class='txt-color-orangeDark'><strong>" + $('#show-shortcut').text() + "</strong></span> ?",
							content : $this.data('logout-msg') || "是否退出系统？",
							buttons : '[取消][确定]'
				
						}, function(ButtonPressed) {
							if (ButtonPressed == "确定") {
								$.root_.addClass('animated fadeOutUp');
								setTimeout(logout, 1000);
							}
						});
						function logout() {
							window.location = $this.attr('href');
						}
				
					},

					// RESET WIDGETS
				    resetWidgets: function($this){
						$.widresetMSG = $this.data('reset-msg');
						
						$.SmartMessageBox({
							title : "<i class='fa fa-refresh' style='color:green'></i> Clear Local Storage",
							content : $.widresetMSG || "Would you like to RESET all your saved widgets and clear LocalStorage?",
							buttons : '[No][Yes]'
						}, function(ButtonPressed) {
							if (ButtonPressed == "Yes" && localStorage) {
								localStorage.clear();
								location.reload();
							}
				
						});
				    },
				    
				    // LAUNCH FULLSCREEN 
				    launchFullscreen: function(element){
				
						if (!$.root_.hasClass("full-screen")) {
					
							$.root_.addClass("full-screen");
					
							if (element.requestFullscreen) {
								element.requestFullscreen();
							} else if (element.mozRequestFullScreen) {
								element.mozRequestFullScreen();
							} else if (element.webkitRequestFullscreen) {
								element.webkitRequestFullscreen();
							} else if (element.msRequestFullscreen) {
								element.msRequestFullscreen();
							}
					
						} else {
							
							$.root_.removeClass("full-screen");
							
							if (document.exitFullscreen) {
								document.exitFullscreen();
							} else if (document.mozCancelFullScreen) {
								document.mozCancelFullScreen();
							} else if (document.webkitExitFullscreen) {
								document.webkitExitFullscreen();
							}
					
						}
				
				   },
				
				   // MINIFY MENU
				    minifyMenu: function($this){
				    	if (!$.root_.hasClass("menu-on-top")){
							$.root_.toggleClass("minified");
							$.root_.removeClass("hidden-menu");
							$('html').removeClass("hidden-menu-mobile-lock");
							$this.effect("highlight", {}, 500);
						}
				    },
				    
				    // TOGGLE MENU 
				    toggleMenu: function(){
				    	if (!$.root_.hasClass("menu-on-top")){
							$('html').toggleClass("hidden-menu-mobile-lock");
							$.root_.toggleClass("hidden-menu");
							$.root_.removeClass("minified");
				    	} else if ( $.root_.hasClass("menu-on-top") && $.root_.hasClass("mobile-view-activated") ) {
				    		$('html').toggleClass("hidden-menu-mobile-lock");
							$.root_.toggleClass("hidden-menu");
							$.root_.removeClass("minified");
				    	}
				    },     
				
				    // TOGGLE SHORTCUT 
				    toggleShortcut: function(){
				    	
						if ($.shortcut_dropdown.is(":visible")) {
							shortcut_buttons_hide();
						} else {
							shortcut_buttons_show();
						}

						// SHORT CUT (buttons that appear when clicked on user name)
						$.shortcut_dropdown.find('a').click(function(e) {
							e.preventDefault();
							window.location = $(this).attr('href');
							setTimeout(shortcut_buttons_hide, 300);
					
						});
					
						// SHORTCUT buttons goes away if mouse is clicked outside of the area
						$(document).mouseup(function(e) {
							if (!$.shortcut_dropdown.is(e.target) && $.shortcut_dropdown.has(e.target).length === 0) {
								shortcut_buttons_hide();
							}
						});
						
						// SHORTCUT ANIMATE HIDE
						function shortcut_buttons_hide() {
							$.shortcut_dropdown.animate({
								height : "hide"
							}, 300, "easeOutCirc");
							$.root_.removeClass('shortcut-on');
					
						}
					
						// SHORTCUT ANIMATE SHOW
						function shortcut_buttons_show() {
							$.shortcut_dropdown.animate({
								height : "show"
							}, 200, "easeOutCirc");
							$.root_.addClass('shortcut-on');
						}
				
				    }  
				   
				};
				
				var actionEvents = {
					userLogout: function(e) {
						smartActions.userLogout(element);
					},
					resetWidgets: function(e) {
						smartActions.resetWidgets(element);
					},
					launchFullscreen: function(e) {
						smartActions.launchFullscreen(document.documentElement);
					},
					minifyMenu: function(e) {
						smartActions.minifyMenu(element);
					},
					toggleMenu: function(e) {
						smartActions.toggleMenu();
					},
					toggleShortcut: function(e) {
						smartActions.toggleShortcut();
					}
				};

				if (angular.isDefined(attrs.action) && attrs.action != '') {
					var actionEvent = actionEvents[attrs.action];
					if (typeof actionEvent === 'function') {
						element.on('click', function(e) {
							actionEvent(e);
							e.preventDefault();
						});
					}
				}

			}
		};
	})

	.directive('header', function() {
		return {
			restrict: 'EA',
			link: function(scope, element, attrs) {
				// SHOW & HIDE MOBILE SEARCH FIELD
				angular.element('#search-mobile').click(function() {
					$.root_.addClass('search-mobile');
				});

				angular.element('#cancel-search-js').click(function() {
					$.root_.removeClass('search-mobile');
				});
			}
		};
	})

	.directive('ribbon', function() {
		return {
			restrict: 'A',
			link: function(scope, element, attrs) {
				element.append('<div class="demo"><span id="demo-setting"><i class="fa fa-cog txt-color-blueDark"></i></span> <form><legend class="no-padding margin-bottom-10">Layout Options</legend><section><label><input name="subscription" id="smart-fixed-header" type="checkbox" class="checkbox style-0"><span>Fixed Header</span></label><label><input type="checkbox" name="terms" id="smart-fixed-navigation" class="checkbox style-0"><span>Fixed Navigation</span></label><label><input type="checkbox" name="terms" id="smart-fixed-ribbon" class="checkbox style-0"><span>Fixed Ribbon</span></label><label><input type="checkbox" name="terms" id="smart-fixed-footer" class="checkbox style-0"><span>Fixed Footer</span></label><label><input type="checkbox" name="terms" id="smart-fixed-container" class="checkbox style-0"><span>Inside <b>.container</b> <div class="fonts-xs text-right">(non-responsive)</div></span></label><label style="display:block;"><input type="checkbox" id="smart-topmenu" class="checkbox style-0"><span>Menu on <b>top</b></span></label> <span id="smart-bgimages"></span></section><section><h6 class="margin-top-10 semi-bold margin-bottom-5">Clear Localstorage</h6><a href="javascript:void(0);" class="btn btn-xs btn-block btn-primary" id="reset-smart-widget"><i class="fa fa-refresh"></i> Factory Reset</a></section> <h6 class="margin-top-10 semi-bold margin-bottom-5">SmartAdmin Skins</h6><section id="smart-styles"><a href="javascript:void(0);" id="smart-style-0" data-skinlogo="img/logo.png" class="btn btn-block btn-xs txt-color-white margin-right-5" style="background-color:#4E463F;"><i class="fa fa-check fa-fw" id="skin-checked"></i>Smart Default</a><a href="javascript:void(0);" id="smart-style-1" data-skinlogo="img/logo-white.png" class="btn btn-block btn-xs txt-color-white" style="background:#3A4558;">Dark Elegance</a><a href="javascript:void(0);" id="smart-style-2" data-skinlogo="img/logo-blue.png" class="btn btn-xs btn-block txt-color-darken margin-top-5" style="background:#fff;">Ultra Light</a><a href="javascript:void(0);" id="smart-style-3" data-skinlogo="img/logo-pale.png" class="btn btn-xs btn-block txt-color-white margin-top-5" style="background:#f78c40">Google Skin</a></section></form> </div>')

				// hide bg options
				var smartbgimage = "<h6 class='margin-top-10 semi-bold'>Background</h6><img src='img/pattern/graphy-xs.png' data-htmlbg-url='img/pattern/graphy.png' width='22' height='22' class='margin-right-5 bordered cursor-pointer'><img src='img/pattern/tileable_wood_texture-xs.png' width='22' height='22' data-htmlbg-url='img/pattern/tileable_wood_texture.png' class='margin-right-5 bordered cursor-pointer'><img src='img/pattern/sneaker_mesh_fabric-xs.png' width='22' height='22' data-htmlbg-url='img/pattern/sneaker_mesh_fabric.png' class='margin-right-5 bordered cursor-pointer'><img src='img/pattern/nistri-xs.png' data-htmlbg-url='img/pattern/nistri.png' width='22' height='22' class='margin-right-5 bordered cursor-pointer'><img src='img/pattern/paper-xs.png' data-htmlbg-url='img/pattern/paper.png' width='22' height='22' class='bordered cursor-pointer'>";
				$("#smart-bgimages")
				    .fadeOut();

				$('#demo-setting')
				    .click(function () {
				        //console.log('setting');
				        $('#ribbon .demo')
				            .toggleClass('activate');
				    })
				/*
				 * FIXED HEADER
				 */
				$('input[type="checkbox"]#smart-fixed-header')
				    .click(function () {
				        if ($(this)
				            .is(':checked')) {
				            //checked
				            $.root_.addClass("fixed-header");
				        } else {
				            //unchecked
				            $('input[type="checkbox"]#smart-fixed-ribbon')
				                .prop('checked', false);
				            $('input[type="checkbox"]#smart-fixed-navigation')
				                .prop('checked', false);

				            $.root_.removeClass("fixed-header");
				            $.root_.removeClass("fixed-navigation");
				            $.root_.removeClass("fixed-ribbon");

				        }
				    });

				/*
				 * FIXED NAV
				 */
				$('input[type="checkbox"]#smart-fixed-navigation')
				    .click(function () {
				        if ($(this)
				            .is(':checked')) {
				            //checked
				            $('input[type="checkbox"]#smart-fixed-header')
				                .prop('checked', true);

				            $.root_.addClass("fixed-header");
				            $.root_.addClass("fixed-navigation");

				            $('input[type="checkbox"]#smart-fixed-container')
				                .prop('checked', false);
				            $.root_.removeClass("container");

				        } else {
				            //unchecked
				            $('input[type="checkbox"]#smart-fixed-ribbon')
				                .prop('checked', false);
				            $.root_.removeClass("fixed-navigation");
				            $.root_.removeClass("fixed-ribbon");
				        }
				    });

				/*
				 * FIXED RIBBON
				 */
				$('input[type="checkbox"]#smart-fixed-ribbon')
				    .click(function () {
				        if ($(this)
				            .is(':checked')) {

				            //checked
				            $('input[type="checkbox"]#smart-fixed-header')
				                .prop('checked', true);
				            $('input[type="checkbox"]#smart-fixed-navigation')
				                .prop('checked', true);
				            $('input[type="checkbox"]#smart-fixed-ribbon')
				                .prop('checked', true);

				            //apply
				            $.root_.addClass("fixed-header");
				            $.root_.addClass("fixed-navigation");
				            $.root_.addClass("fixed-ribbon");

				            $('input[type="checkbox"]#smart-fixed-container')
				                .prop('checked', false);
				            $.root_.removeClass("container");

				        } else {
				            //unchecked
				            $.root_.removeClass("fixed-ribbon");
				        }
				    });

				/*
				 * RTL SUPPORT
				 */
				$('input[type="checkbox"]#smart-fixed-footer')
				    .click(function () {
				        if ($(this)
				            .is(':checked')) {

				            //checked
				            $.root_.addClass("fixed-page-footer");

				        } else {
				            //unchecked
				            $.root_.removeClass("fixed-page-footer");
				        }
				    });


				/*
				 * RTL SUPPORT
				 */
				$('input[type="checkbox"]#smart-rtl')
				    .click(function () {
				        if ($(this)
				            .is(':checked')) {

				            //checked
				            $.root_.addClass("smart-rtl");

				        } else {
				            //unchecked
				            $.root_.removeClass("smart-rtl");
				        }
				    });

				/*
				 * MENU ON TOP
				 */

				$('#smart-topmenu')
				    .on('change', function (e) {
				        if ($(this)
				            .prop('checked')) {
				            //window.location.href = '?menu=top';
				            localStorage.setItem('sm-setmenu', 'top');
				            location.reload();
				        } else {
				            //window.location.href = '?';
				            localStorage.setItem('sm-setmenu', 'left');
				            location.reload();
				        }
				    });

				if (localStorage.getItem('sm-setmenu') == 'top') {
				    $('#smart-topmenu')
				        .prop('checked', true);
				} else {
				    $('#smart-topmenu')
				        .prop('checked', false);
				}

				/*
				 * INSIDE CONTAINER
				 */
				$('input[type="checkbox"]#smart-fixed-container')
				    .click(function () {
				        if ($(this)
				            .is(':checked')) {
				            //checked
				            $.root_.addClass("container");

				            $('input[type="checkbox"]#smart-fixed-ribbon')
				                .prop('checked', false);
				            $.root_.removeClass("fixed-ribbon");

				            $('input[type="checkbox"]#smart-fixed-navigation')
				                .prop('checked', false);
				            $.root_.removeClass("fixed-navigation");

				            if (smartbgimage) {
				                $("#smart-bgimages")
				                    .append(smartbgimage)
				                    .fadeIn(1000);
				                $("#smart-bgimages img")
				                    .bind("click", function () {
				                        var $this = $(this);
				                        var $html = $('html')
				                        bgurl = ($this.data("htmlbg-url"));
				                        $html.css("background-image", "url(" + bgurl + ")");
				                    })
				                smartbgimage = null;
				            } else {
				                $("#smart-bgimages")
				                    .fadeIn(1000);
				            }

				        } else {
				            //unchecked
				            $.root_.removeClass("container");
				            $("#smart-bgimages")
				                .fadeOut();
				        }
				    });

				/*
				 * REFRESH WIDGET
				 */
				$("#reset-smart-widget")
				    .bind("click", function () {
				        $('#refresh')
				            .click();
				        return false;
				    });

				/*
				 * STYLES
				 */
				$("#smart-styles > a")
				   .on('click', function() {
				        var $this = $(this);
				        var $logo = $("#logo img");
				        $.root_.removeClassPrefix('smart-style')
				            .addClass($this.attr("id"));
				        $logo.attr('src', $this.data("skinlogo"));
				        $("#smart-styles > a #skin-checked")
				            .remove();
				        $this.prepend("<i class='fa fa-check fa-fw' id='skin-checked'></i>");
				    });
			}
		};
	})

	.controller('breadcrumbController', ['$scope', function($scope) {
		$scope.breadcrumbs = [];
		$scope.$on('navItemSelected', function(name, crumbs) {
			$scope.setBreadcrumb(crumbs);
		});

		$scope.setBreadcrumb = function(crumbs) {
			$scope.breadcrumbs = crumbs;
		}
	}])

	.directive('breadcrumb', ['ribbon', 'localize', '$compile', function(ribbon, localize, $compile) {
		return {
			restrict: 'AE',
			controller: 'breadcrumbController',
			replace: true,
			link: function(scope, element, attrs) {
				scope.$watch('breadcrumbs', function(newVal, oldVal) {
					if (newVal !== oldVal) {
						// update DOM
						scope.updateDOM();
					}
				})
				scope.updateDOM = function() {
					element.empty();
					angular.forEach(scope.breadcrumbs, function(crumb) {
						var li = angular.element('<li data-localize="' + crumb + '">' + crumb + '</li>');
						li.text(localize.localizeText(crumb));
						
						$compile(li)(scope);
						element.append(li);
					});
				};
				
				// set the current breadcrumb on load
				scope.setBreadcrumb(ribbon.currentBreadcrumb);
				scope.updateDOM();
			},
			template: '<ol class="breadcrumb"></ol>'
		}
	}])
;

// directives for localization
angular.module('app.localize', [])

	.factory('localize', ['$http', '$rootScope', '$window', function($http, $rootScope, $window){
		var localize = {
			currentLocaleData: {},
			currentLang: {},
			setLang: function(lang) {
				$http({method: 'GET', url: localize.getLangUrl(lang), cache: false})
				.success(function(data) {
					localize.currentLocaleData = data;
					localize.currentLang = lang;
					$rootScope.$broadcast('localizeLanguageChanged');
				}).error(function(data) {
					console.log('Error updating language!');
				})
			},
			getLangUrl: function(lang) {
				return 'js/langs/' + lang.langCode + '.js';
			},

			localizeText: function(sourceText) {
				return localize.currentLocaleData[sourceText];
			}
		};

		return localize;
	}])

	.directive('localize', ['localize', function(localize) {
		return {
			restrict: 'A',
			scope: {
				sourceText: '@localize'
			},
			link: function(scope, element, attrs) {
				scope.$on('localizeLanguageChanged', function() {
					var localizedText = localize.localizeText(scope.sourceText);
					if (element.is('input, textarea')) element.attr('placeholder', localizedText)
					else element.text(localizedText);
				});
			}
		}
	}])
;

// directives for navigation
angular.module('app.navigation', [])
	.directive('navigation', function() {
		return {
			restrict: 'AE',
			controller: ['$scope', function($scope) {

			}],
			transclude: true,
			replace: true,
			link: function(scope, element, attrs) {
				if (!$topmenu) {
					if (!null) {
						element.first().jarvismenu({
							accordion : true,
							speed : $.menu_speed,
							closedSign : '<em class="fa fa-plus-square-o"></em>',
							openedSign : '<em class="fa fa-minus-square-o"></em>'
						});
					} else {
						alert("Error - menu anchor does not exist");
					}
				}

				// SLIMSCROLL FOR NAV
				if ($.fn.slimScroll) {
					element.slimScroll({
				        height: '100%'
				    });
				}

				scope.getElement = function() {
					return element;
				}
			},
			template: '<nav><ul data-ng-transclude=""></ul></nav>'
		};
	})

	.controller('NavGroupController', ['$scope', function($scope) {
		$scope.active = false;
		$scope.hasIcon = angular.isDefined($scope.icon);
	    $scope.hasIconCaption = angular.isDefined($scope.iconCaption);

	    this.setActive = function(active) {
	    	$scope.active = active;
	    }

	}])
	.directive('navGroup', function() {
		return {
			restrict: 'AE',
			controller: 'NavGroupController',
			transclude: true,
			replace: true,
			scope: {
				icon: '@',
				title: '@',
				iconCaption: '@',
				active: '@'
			},
			template: '\
				<li data-ng-class="{active: active}" >\
					<a href="">\
						<i data-ng-if="hasIcon" class="{{ icon }}"><em data-ng-if="hasIconCaption"> {{ iconCaption }} </em></i>\
						<span class="menu-item-parent" data-localize="{{ title }}">{{ title }}</span>\
					</a>\
					<ul data-ng-transclude=""></ul>\
				</li>',

		};
	})

	.controller('NavItemController', ['$rootScope', '$scope', '$location',"$state", function($rootScope, $scope, $location, $state) {
		$scope.isChild = false;
		$scope.active = false;
		$scope.isActive = function (viewLocation) {
			$scope.active = viewLocation === "#" + $location.path();
			return $scope.active;
	    };

	    $scope.hasIcon = angular.isDefined($scope.icon);
	    $scope.hasIconCaption = angular.isDefined($scope.iconCaption);

	    $scope.getItemUrl = function(view) {
            //if (angular.isDefined($scope.href)){
			//	return $scope.href
			//};
	    	if (!angular.isDefined(view)) {
				return ''
			};
	    	return view;
	    };

	    $scope.getItemTarget = function() {
	    	return angular.isDefined($scope.target) ? $scope.target : '_self';
	    };

	}])
	.directive('navItem', ['ribbon', '$window', '$rootScope', '$state', function(ribbon, $window, $rootScope, $state) {
		return {
			require: ['^navigation', '^?navGroup'],
			restrict: 'AE',
			controller: 'NavItemController',
			scope: {
				title: '@',
				uiSref: '@',
				icon: '@',
				iconCaption: '@',
				href: '@',
				target: '@'
			},
			link: function(scope, element, attrs, parentCtrls) {
				var navCtrl = parentCtrls[0],
					navgroupCtrl = parentCtrls[1]

				scope.$watch('active', function(newVal, oldVal) {
					if (newVal) {
						if (angular.isDefined(navgroupCtrl) && navgroupCtrl != null){
							navgroupCtrl.setActive(true);
						}
						$window.document.title = scope.title;
						scope.setBreadcrumb();
					} else {
						if (angular.isDefined(navgroupCtrl) && navgroupCtrl != null){
							navgroupCtrl.setActive(false);
						}
					}
				});
				scope.openParents = scope.isActive(scope.href);
				scope.isChild = angular.isDefined(navgroupCtrl);
				
				scope.setBreadcrumb = function() {
	    			var crumbs = [];
	    			crumbs.push(scope.title);
	    			// get parent menus
					var test = element.parents('nav li').each(function() {
						var el = angular.element(this);
						var parent = el.find('.menu-item-parent:eq(0)');
						crumbs.push(parent.data('localize').trim());
						if (scope.openParents) {
							// open menu on first load
							parent.trigger('click');
						}
					});
					// this should be only fired upon first load so let's set this to false now
					scope.openParents = false;
					ribbon.updateBreadcrumb(crumbs);
	    		};
				scope.isItemActive = function(uiSref){
					if($state.current.name == uiSref){
						return true;
					}
					return false;
				}
	    		element.on('click', 'a[href!="#"]', function() {
	    			if ($.root_.hasClass('mobile-view-activated')) {
	    				$.root_.removeClass('hidden-menu');
	    				$('html').removeClass("hidden-menu-mobile-lock");
	    			}
					$rootScope.$apply();
	    		});
				
			},
			transclude: true,
			replace: true,
			template: '\
				<li ng-class="{active:isItemActive(uiSref)}">\
					<a ui-sref="{{ getItemUrl(uiSref) }}"  title="{{ title }}">\
						<i data-ng-if="hasIcon" class="{{ icon }}"><em data-ng-if="hasIconCaption"> {{ iconCaption }} </em></i>\
						<span ng-class="{\'menu-item-parent\': !isChild}" data-localize="{{ title }}"> {{ title }} </span>\
						<span data-ng-transclude=""></span>\
					</a>\
				</li>'
		};
	}])
;

// directives for activity
angular.module('app.activity', [])
	.controller('ActivityController', ['$scope', '$http' , function($scope, $http) {
		var ctrl = this,
			items = ctrl.items = $scope.items = [];

		ctrl.loadItem = function(loadedItem, callback) {
			angular.forEach(items, function(item) {
		      	if (item.active && item !== loadedItem) {
		        	item.active = false;
		        	//item.onDeselect();
		      	}
		    });

			loadedItem.active = true;
			if (angular.isDefined(loadedItem.onLoad)) {
				loadedItem.onLoad(loadedItem);
			}

			$http.get(loadedItem.src).then(function(result) {
				var content = result.data;
				if (angular.isDefined(callback)) {
					callback(content);
				}
			});
		};

		ctrl.addItem = function(item) {
			items.push(item);
			if (!angular.isDefined(item.active)) {
				// set the default
				item.active = false;
			} else if (item.active) {
				ctrl.loadItem(item);
			}
		};

		ctrl.refresh = function(e) {
			var btn = angular.element(e.currentTarget);
			btn.button('loading');

			if (angular.isDefined($scope.onRefresh)) {
				$scope.onRefresh($scope, function() {
					btn.button('reset');
				});
			} else {
				btn.button('reset');
			}
		};
	}])

	.directive('activity', function() {
		return {
			restrict: 'AE',
			replace: true,
			transclude: true,
			controller: 'ActivityController',
			scope: {
				onRefresh: '=onrefresh',
			},
			template: '<span data-ng-transclude=""></span>'
		};
	})

	.directive('activityButton', function() {
		return {
			restrict: 'AE',
			require: '^activity',
			replace: true,
			transclude: true,
			controller: function($scope) {

			},
			scope: {
				icon: '@',
				total: '='
			},
			template: '\
					<span id="activity" class="activity-dropdown">\
						<i ng-class="icon"></i>\
						<b class="badge"> {{ total }} </b>\
					</span>',
			link: function(scope, element, attrs, activityCtrl) {

				attrs.$observe('icon', function(value) {
					if (!angular.isDefined(value))
						scope.icon = 'fa fa-user';
				});

				element.on('click', function(e) {
					var $this = $(this);

					if ($this.find('.badge').hasClass('bg-color-red')) {
						$this.find('.badge').removeClassPrefix('bg-color-');
						$this.find('.badge').text("0");
						// console.log("Ajax call for activity")
					}

					if (!$this.next('.ajax-dropdown').is(':visible')) {
						$this.next('.ajax-dropdown').fadeIn(150);
						$this.addClass('active');
					} else {
						$this.next('.ajax-dropdown').fadeOut(150);
						$this.removeClass('active');
					}

					var mytest = $this.next('.ajax-dropdown').find('.btn-group > .active > input').attr('id');
					//console.log(mytest)

					e.preventDefault();
				});

				if (scope.total > 0) {
					var $badge = element.find('.badge');
					$badge.addClass("bg-color-red bounceIn animated");
				}
			}
		};
	})

	.controller('ActivityContentController', ['$scope' , function($scope) {
		var ctrl = this;
		$scope.currentContent = '';
		ctrl.loadContent = function(content) {
			$scope.currentContent = content;
		}
	}])

	.directive('activityContent', ['$compile', function($compile) {
		return {
			restrict: 'AE',
			transclude: true,
			require: '^activity',
			replace: true,
			scope: {
				footer: '=?'
			},
			controller: 'ActivityContentController',
			template: '\
				<div class="ajax-dropdown">\
					<div class="btn-group btn-group-justified" data-toggle="buttons" data-ng-transclude=""></div>\
					<div class="ajax-notifications custom-scroll">\
						<div class="alert alert-transparent">\
							<h4>Click a button to show messages here</h4>\
							This blank page message helps protect your privacy, or you can show the first message here automatically.\
						</div>\
						<i class="fa fa-lock fa-4x fa-border"></i>\
					</div>\
					<span> {{ footer }}\
						<button type="button" data-loading-text="Loading..." data-ng-click="refresh($event)" class="btn btn-xs btn-default pull-right" data-activty-refresh-button="">\
						<i class="fa fa-refresh"></i>\
						</button>\
					</span>\
				</div>',
			link: function(scope, element, attrs, activityCtrl) {
				scope.refresh = function(e) {
					activityCtrl.refresh(e);
				};

				scope.$watch('currentContent', function(newContent, oldContent) {
					if (newContent !== oldContent) {
						var el = element.find('.ajax-notifications').html(newContent);
						$compile(el)(scope);
					}
				});
			}
		};
	}])

	.directive('activityItem', function() {
		return {
			restrict: 'AE',
			require: ['^activity', '^activityContent'],
			scope: {
				src: '=',
				onLoad: '=onload',
				active: '=?'
			},
			controller: function() {

			},
			transclude: true,
			replace: true,
			template: '\
				<label class="btn btn-default" data-ng-click="loadItem()" ng-class="{active: active}">\
					<input type="radio" name="activity">\
					<span data-ng-transclude=""></span>\
				</label>',
			link: function(scope, element, attrs, parentCtrls) {
				var activityCtrl = parentCtrls[0],
					contentCtrl = parentCtrls[1];

				scope.$watch('active', function(active) {
					if (active) {
						activityCtrl.loadItem(scope, function(content) {
							contentCtrl.loadContent(content);
						});
					}
				});
				activityCtrl.addItem(scope);

				scope.loadItem = function() {
					scope.active = true;
				};
			}
		};
	})

;

angular.module('app.smartui', [])
	.directive('widgetGrid', function() {
		return {
			restrict: 'AE',
			link: function(scope, element, attrs) {
				scope.setup_widget_desktop = function() {
					if ($.fn.jarvisWidgets && $.enableJarvisWidgets) {
						element.jarvisWidgets({
							grid : 'article',
							widgets : '.jarviswidget',
							localStorage : true,
							deleteSettingsKey : '#deletesettingskey-options',
							settingsKeyLabel : 'Reset settings?',
							deletePositionKey : '#deletepositionkey-options',
							positionKeyLabel : 'Reset position?',
							sortable : true,
							buttonsHidden : false,
							// toggle button
							toggleButton : true,
							toggleClass : 'fa fa-minus | fa fa-plus',
							toggleSpeed : 200,
							onToggle : function() {
							},
							// delete btn
							deleteButton : true,
							deleteClass : 'fa fa-times',
							deleteSpeed : 200,
							onDelete : function() {
							},
							// edit btn
							editButton : true,
							editPlaceholder : '.jarviswidget-editbox',
							editClass : 'fa fa-cog | fa fa-save',
							editSpeed : 200,
							onEdit : function() {
							},
							// color button
							colorButton : true,
							// full screen
							fullscreenButton : true,
							fullscreenClass : 'fa fa-expand | fa fa-compress',
							fullscreenDiff : 3,
							onFullscreen : function() {
							},
							// custom btn
							customButton : false,
							customClass : 'folder-10 | next-10',
							customStart : function() {
								alert('Hello you, this is a custom button...');
							},
							customEnd : function() {
								alert('bye, till next time...');
							},
							// order
							buttonOrder : '%refresh% %custom% %edit% %toggle% %fullscreen% %delete%',
							opacity : 1.0,
							dragHandle : '> header',
							placeholderClass : 'jarviswidget-placeholder',
							indicator : true,
							indicatorTime : 600,
							ajax : true,
							timestampPlaceholder : '.jarviswidget-timestamp',
							timestampFormat : 'Last update: %m%/%d%/%y% %h%:%i%:%s%',
							refreshButton : true,
							refreshButtonClass : 'fa fa-refresh',
							labelError : 'Sorry but there was a error:',
							labelUpdated : 'Last Update:',
							labelRefresh : 'Refresh',
							labelDelete : 'Delete widget:',
							afterLoad : function() {
							},
							rtl : false, // best not to toggle this!
							onChange : function() {

							},
							onSave : function() {

							},
							ajaxnav : $.navAsAjax // declears how the localstorage should be saved (HTML or AJAX page)

						});
					}
				}

				scope.setup_widget_mobile = function() {
					if ($.enableMobileWidgets && $.enableJarvisWidgets) {
						scope.setup_widgets_desktop();
					}
				}

				if ($.device === "desktop") {
					scope.setup_widget_desktop();
				} else {
					scope.setup_widget_mobile();
				}
				
			}
		}
	})

	.directive('widget', function() {
		return {
			restrict: 'AE',
			transclude: true,
			replace: true,
			template: '<div class="jarviswidget" data-ng-transclude=""></div>'
		}
	})

	.directive('widgetHeader', function() {
		return {
			restrict: 'AE',
			transclude: true,
			replace: true,
			scope: {
				title: '@',
				icon: '@'
			},
			template: '\
				<header>\
					<span class="widget-icon"> <i data-ng-class="icon"></i> </span>\
					<h2>{{ title }}</h2>\
					<span data-ng-transclude></span>\
				</header>'
		}
	})

	.directive('widgetToolbar', function() {
		return {
			restrict: 'AE',
			transclude: true,
			replace: true,
			template: '<div class="widget-toolbar" data-ng-transclude=""></div>'
		}
	})

	.directive('widgetEditbox', function() {
		return {
			restrict: 'AE', 
			transclude: true, 
			replace: true,
			template: '<div class="jarviswidget-editbox" data-ng-transclude=""></div>'
		}
	})

	.directive('widgetBody', function() {
		return {
			restrict: 'AE',
			transclude: true,
			replace: true,
			template: '<div data-ng-transclude=""></div>'
		}
	})

	.directive('widgetBodyToolbar', function() {
		return {
			restrict: 'AE',
			transclude: true,
			replace: true,
			scope: {
				class: '@'
			},
			template: '<div class="widget-body-toolbar" data-ng-transclude=""></div>'
		}
	})

	.directive('widgetContent', function() {
		return {
			restrict: 'AE',
			transclude: true,
			replace: true,
			template: '<div class="widget-body" data-ng-transclude=""></div>'
		}
	})