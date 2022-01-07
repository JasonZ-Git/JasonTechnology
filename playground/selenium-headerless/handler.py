from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import time

def main(event, context):
    options = Options()
    options.binary_location = '/opt/headless-chromium'
    options.add_argument('--headless')
    options.add_argument('--no-sandbox')
    options.add_argument('--single-process')
    options.add_argument('--disable-dev-shm-usage')

    driver = webdriver.Chrome('/opt/chromedriver',chrome_options=options)
    driver.get('https://translate.google.com/?sl=en&tl=zh-CN&text=returns&op=translate')
    
    time.sleep(4);
    try:
        translation = driver.find_element_by_css_selector("span[data-language-for-alternatives='zh-CN'] > span")
        main_translation = translation.text;
        print(main_translation)    
    except:
        print("probably no translation");
    
    other_translations = list();
    try:
        other_translations = driver.find_element_by_css_selector("span[data-term-type='tl']");
    except:
        print("probably no translation");
    
    all_translations = set();
    
    if main_translation is not None:
        all_translations.add(main_translation)
    
    if other_translations is not None:
        for current in other_translations:
            if current.text not in all_translations:
                all_translations.add(current.text)

    response = {
        "statusCode": 200,
        "body": "All translation is: " + str(all_translations)
    }
    
    driver.quit();

    return response
