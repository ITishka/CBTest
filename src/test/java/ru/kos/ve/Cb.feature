# language: ru
@all
Функция: тестовое задание

  Сценарий: тестовое задание
    Когда пользователь на странице https://www.google.ru/
    К тому же отображается поле "lst-ib"
    Тогда вводим в поле "lst-ib" "Центральный банк РФ"
    Когда пользователь нажмет клавишу ENTER
    И пользователь нажмет на ссылку "Центральный банк Российской Федерации"
    Тогда пользователь на полностью загруженном сайте https://www.cbr.ru/
    Когда пользователь нажмет на ссылку "Интернет-приемная"
    Когда пользователь нажмет на ссылку "Написать благодарность"
    Тогда вводим в поле "MessageBody" "Благодарно благодарю!"
    И чек бокс "Я согласен"
    Когда делаем скриншот
    Тогда отправляем скриншот на e-mail "vetkas86@gmail.com"






