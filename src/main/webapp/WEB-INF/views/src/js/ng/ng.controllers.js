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
 * original filename: ng.controllers.js
 * author: Sunny (@bootstraphunt)
 * email: info@myorange.ca
 */
angular.module("app.controllers", []).factory("settings", ["$rootScope", function (a) {
    var b = {languages: [
        {language: "English", translation: "English", langCode: "en", flagCode: "us"},
        {language: "Espanish", translation: "Espanish", langCode: "es", flagCode: "es"},
        {language: "German", translation: "Deutsch", langCode: "de", flagCode: "de"},
        {language: "Korean", translation: "한국의", langCode: "ko", flagCode: "kr"},
        {language: "French", translation: "français", langCode: "fr", flagCode: "fr"},
        {language: "Portuguese", translation: "português", langCode: "pt", flagCode: "br"},
        {language: "Russian", translation: "русский", langCode: "ru", flagCode: "ru"},
        {language: "Chinese", translation: "中國的", langCode: "zh", flagCode: "cn"}
    ]};
    return b
}]).controller("SmartAppController", ["$scope", function (a) {
}]).controller("LangController", ["$scope", "settings", "localize", function (b, c, a) {
    b.languages = c.languages;
    b.currentLang = c.currentLang;
    b.setLang = function (d) {
        c.currentLang = d;
        b.currentLang = d;
        a.setLang(d)
    };
    b.setLang(b.currentLang)
}]);