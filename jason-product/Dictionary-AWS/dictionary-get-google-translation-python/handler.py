from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import time

def main(event, context):
    
    word = event['Records'][0]['Sns']['Message']
    print(f"sns message is {word}")
    
    options = Options()
    options.binary_location = '/opt/headless-chromium_57'
    options.add_argument('--headless')
    options.add_argument('--no-sandbox')
    options.add_argument('--single-process')
    options.add_argument('--disable-dev-shm-usage')

    driver = webdriver.Chrome('/opt/chromedriver_57',chrome_options=options)
    driver.get(f'https://translate.google.com/?sl=en&tl=zh-CN&text={word}&op=translate')
    
    time.sleep(4);
    try:
        translation = driver.find_element_by_css_selector("span[data-language-for-alternatives='zh-CN'] > span")
        main_translation = translation.text;
        print(main_translation)    
    except:
        print("probably no translation");
        return {
            "statusCode": 404,
            "word": word,
            "translation": "No Translation Found"
        }
        
    all_translations = list();
    
    all_translations.append(main_translation)
    
    other_translations = list();
    try:
        other_translations = driver.find_elements_by_css_selector("span[data-term-type='tl']");
    except:
        print("probably no translation - continue");
    
    print(f"other translation is {other_translations}")
    
    if other_translations is not None:
        for current in other_translations:
            if current.text not in all_translations:
                all_translations.append(current.text)

    translation = ", ".join(str(e) for e in all_translations)
    response = {
        "statusCode": 200,
        "word": word,
        "translation": translation
    }

    driver.quit();

    return response
