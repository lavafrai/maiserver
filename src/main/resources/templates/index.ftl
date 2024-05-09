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
                <@buttons.button icon="download" icon_type="solid" href="#" modal="#download_modal">
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
                <h3>
                    Студентам
                </h3>
                <ul>
	                <li>Отображение расписания и его локальное сохранение</li>
                    <li>Удобный виджет показывающий ваше расписание</li>
                    <li>Возможность загрузить несколько расписаний и переключаться между ними</li>
                    <li>Информация о студгородке и карта кампуса</li>
                    <li>Расписание обновляется автоматически при каждом запуске приложения</li>
                    <li>Отзывы на преподавателей, при сотрудничестве с <a href="https://mai-exler.ru/">МАИ.Экслер.ру</a></li>
                    <li>Вы можете отметить работы, чтобы не забыть о предстоящих контрольных и проверочных</li>
                    <li>Отдельная вкладка для лабораторных и помеченных Вами работ, а также для экзаменов</li>
                </ul>
                <h3>
                Преподавателям
                </h3>
                <ul>
                    <li>Красивое отображение расписания</li>
                    <li>Своевременное обновление расписания</li>
                    <li>Удобный виджет для отображения пар прямо на домашнем экране</li>
                    <li>Вы можете отметить предстоящие работы или оставить для себя заметку на следующую пару</li>
                </ul>
                <h2>
                    Благодарности
                </h2>
                <ul>
                    <li>Сбор данных: Maximon0101</li>
                    <li>Монтаж и дизайн: ArtemBay</li>
                    <li>Тестирование: Артем Помощников</li>
                    <li>Сотрудничество с экслером: Суворов</li>
                </ul>
            </section>
        </main>
        <footer>
            <div>
                <div>(c) 2024 - lava_frai (Владимир Курдюков)</div>
                <div>With <i class="fa fa-heart"></i> by. @lava_frai</div>
                <div id="footer-contacts">
                    <a href="https://github.com/lavaFrai/mai3"><i class="fa fa-github"></i></a>
                    <a href="mailto:lavafrai@yandex.ru"><i class="fa fa-envelope"></i></a>
                    <a href="https://t.me/maiapp3"><i class="fa fa-telegram"></i></a>
                </div>
            </div>
        </footer>

        <div class="hystmodal" id="download_modal" aria-hidden="true">
            <div class="hystmodal__wrap">
                <div class="hystmodal__window" role="dialog" aria-modal="true">
                    <button data-hystclose class="hystmodal__close">Close</button>
                    <h3 style="color: var(--foreground-superinaccent); margin-bottom: 16px;">MAI app</h3>
                    <p>Не знаете, как установить приложение на Android? <a href="https://googlegiksearch.github.io/?q=How+to+install+apk">Посмотрите тут</a></p>
                    <hr/>
                    <p>Как хотите загрузить?</p>

                    <a href="https://t.me/maiapp3">
                        <div class="card flex flex-row">
                            <i class="fa-brands fa-telegram fa-2x" style="color: var(--foreground-superinaccent);"></i>
                            <div class="flex flex-column" style="margin-left: 16px;">
                                <h3>Telegram</h3>
                                <p style="margin-top: 8px;">Новейшая версия из нашего телеграм канала</p>
                            </div>
                        </div>
                    </a>

                    <a href="https://apps.rustore.ru/app/ru.lavafrai.maiapp">
                        <div class="card flex flex-row">
                            <img src="/static/media/rustore-light.png" style="height: 2em" class="on-light">
                            <img src="/static/media/rustore-dark.png" style="height: 2em" class="on-dark">
                            <div class="flex flex-column" style="margin-left: 16px;">
                                <h3>RuStore</h3>
                                <p style="margin-top: 8px;">Автоматическое обновление до актуальной версии</p>
                            </div>
                        </div>
                    </a>

                    <a href="https://github.com/lavafrai/mai3">
                        <div class="card flex flex-row">
                            <i class="fa-brands fa-github fa-2x" style="color: var(--foreground-superinaccent);"></i>
                            <div class="flex flex-column" style="margin-left: 16px;">
                                <h3>GitHub</h3>
                                <p style="margin-top: 8px;">Исходный код или новейшая сборка на GitHub</p>
                            </div>
                        </div>
                    </a>

                    <a>
                        <div class="card flex flex-row">
                            <i class="fa-brands fa-google-play fa-2x" style="color: var(--foreground-superinaccent);"></i>
                            <div class="flex flex-column" style="margin-left: 16px;">
                                <h3>Play Store (Coming soon)</h3>
                                <p style="margin-top: 8px;">Скоро мы опубликуемся и здесь</p>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <script>
            const myModal = new HystModal({
                linkAttributeName: "data-hystmodal",
                // settings (optional). see Configuration
            });
        </script>
    </body>
</html>
