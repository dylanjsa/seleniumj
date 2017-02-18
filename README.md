# seleniumj
Makes my Java selenium tests suck less.

There are few technologies in the Java world that I think sucks.
 * Hibernate
 * Seam
 * Selenium
 
Do they suck as a fact, no. Is it possible that I'm just using it incorrectly, Yes - It's possible. I don't care though.
I have a problem, and I need a solution.
Selenium is something that I can't get away from and I want to use it various things I run a grid of 72 nodes testing over 3000 UI tests and it's been nightmarish to finally get it working.

The biggest nightmare for me has been false negatives, tests that say they fail - when they don't actually - if u run it again, it works.

Apart from the larger scale testing, I'd like to somethings just automate something small really quickly, and I really couldn't be bothered with downloading drivers - setting system properties and wiring everything together.

I just wanted something easy, and that's this project.
You can use it if you want, just don't be mad at me if something doesn't work the way you want it, rather create a pull request.

#Getting started
* git clone the repo
* import it into your ide
* add your scrapers/automation classes

## or

* git clone the repo
* build it
* include the jar in your project

#Usage
An extremely simple way to get started is to use the `SimpleSeleniumTestCase`. This is truly simple in that it starts up a preconfigured Chrome browser before a class - and closes it at the end.
If you need something more involved, have a look at the class and create your own utility.

```
public class BasicTest extends SimpleSeleniumTestCase {
  @Test
  public void testTitle() {
    s.go("http://www.google.com");
    WebElement element = s.awaitElement("input");
  }
}
```

I called the helper class _S_ because I couldn't be bother to type out `driver` a hundred times a day.
* You could use `s.getDriver()` if you need more than what's provided.
* `awaitElement` and `awaitElements` can be used to safely find a WebElement by waiting for it to appear, by default the timeout is 6 seconds.
* `awaitElement` and `awaitElements` can take a String, which is a cssSelector (I found that i was using this most), or you can pass the standard *By.cssSelector/By.xpath etc.* into it.
* Any failed tests will generate a screenshot at the time of failure automatically.
* By default a test will only fail, if it failed 3 times.
* It supports a grid, have a look at the code, there isn't much.

