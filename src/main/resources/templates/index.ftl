<html lang="ru">
    <#import "head.ftl" as head>
    <#import "buttons.ftl" as buttons>
    <@head.head/>
    <body>
        <header class="flex flex-row flex-space-between">
            <a class="title" href="https://lavafrai.ru">lava_frai</a>

            <div class="flex">
            <@buttons.button_dark icon="envelope" href="https://lavafrai.ru" mobile="true">Связаться</@buttons.button_dark>
            <@buttons.button_dark icon="heart" href="https://pay.cloudtips.ru/p/e930707c" mobile="true">Пожертвования</@buttons.button_dark>
            </div>
        </header>

        <main>
            <section>
                <div class="flex flex-row">
                    <img src="/static/maiapp_round.webp" width="96" height="96" alt="maiapp logo" class="app-icon">
                    <div>
                        <h1 class="app-title">MAI app</h1>
                        <p>
                            Приложение с информацией и расписанием для студентов и преподавателей Московского Авиационного Института.
                        </p>
                    </div>
                </div>
                <div style="margin-top: 24px;">
                <@buttons.button icon="download" icon_type="solid">
                    Скачать
                </@buttons.button>
                </div>
            </section>
            <section style="margin-top: 24px;">
                <ul id="screenshots" class="flex flex-row">
                <#list 1..6 as i>
                    <li><img src="/static/media/${i}.png" alt="screenshot ${i}"></li>
                </#list>
                </ul>

                <script>
                    let images_element = document.getElementById("screenshots")
                    new Viewer(images_element)
                </script>
            </section>
            <section>
                <h2>Что это?</h2>
                <p>
                    Приложение для быстрого доступа к информации об учебе.
                    Вы можете просматривать расписание вашей группы.
                    Вся информация загружается при первом запуске и доступна без подключения к интернету,
                    так что вы всегда будете иметь расписание пар под рукой.
                </p>
                <h2>
                    Доступные функции
                </h2>
                <ul>
                    <li>Отображение расписания и его локальное сохранение</li>
                    <li>Удобный виджет показывающий ваше расписание</li>
                    <li>Возможность загрузить несколько расписаний и переключаться между ними</li>
                    <li>Информация о студгородке и карта кампуса</li>
                    <li>Расписание обновляется автоматически при каждом запуске приложения</li>
                    <li>Отзывы на преподавателей, при сотрудничестве с <a href="https://mai-exler.ru/">МАИ.Экслер.ру</a></li>
                    <li>Вы можете пометить работы, чтобы не забыть о предстоящих контрольных и проверочных</li>
                    <li>Даже преподаватели могут пользоваться приложением, так же есть режим для абитуриентов</li>
                </ul>
            </section>
        </main>
    </body>
</html>
