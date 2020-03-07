let PATH = "/api/all-news";

$('#myForm input').on('change', function () {
    let message = $('input[name=radioBtn]:checked', '#myForm').val();
    getAllNews(message);
});
const getAllNews = function (message, type, filterBy) {

    fetch(`${PATH}`, {method: 'get'})
        .then(response => response.json())
        .then(data => {

            let result = "";
            if (message === "date") {
                sortByDate(data);
            } else if (message === "title") {
                data.sort((d1, d2) => d1.title.localeCompare(d2.title))
            } else if (message === "date-and-title") {
                sortByDateAndTitle(data);
            } else {
                if (type !== "") {
                    if (type === "date") {
                        data = data.filter(d => d.date === filterBy);
                    } else if (type === "title") {
                        data = data.filter(d => d.title === filterBy);
                    } else if (type === "dateAndTitle") {
                        let params = filterBy.split("And");
                        let date = params[0];
                        let title = params[1];
                        data = data.filter(d => d.title === title && d.date === date);
                    }
                }
            }

            result = createAllNewsTable(data);

            $('.all-news').html(result);
        });
};

getAllNews();

const sortByDate = function (data) {
    data.sort(function (a, b) {
        return new Date(b.date) - new Date(a.date);
    })
};

const sortByTitle = function (data) {
    data.sort(function (a, b) {
        return a.title - b.title;
    })
};

const sortByDateAndTitle = function (data) {
    data.sort(function (a, b) {
        if (b.date !== a.date) {
            return new Date(b.date) - new Date(a.date);
        } else {
            if (a.title < b.title) {
                return -1;
            } else if (a.title > b.title) {
                return 1
            } else {
                return 0;
            }
        }
    })
};

const createAllNewsTable = function (news) {
    let result = `<table class="table table-hover w-100 mx-auto label-color font-weight-bold">
        <thead>
        <tr class="row mx-auto border border-white">
            <th class="col-md-1 text-center font-italic">#</th>
            <th class="col-md-2 text-center font-italic">Date</th>
            <th class="col-md-2 text-center font-italic">Title</th>
            <th class="col-md-2 text-center font-italic">Short Description</th>
            <th class="col-md-5 text-center font-italic">Text</th>
        </tr>
            <tbody>`;

    news.forEach((news, index) => {
        result += allNewsRow(news, index);
    });

    result += `</tbody>
    </table>`;

    return result;
};

const allNewsRow = function (news, index) {
    let table = `
        </thead>
            <tr class="row mx-auto border border-white">
                <td class="col-md-1 text-center">${index + 1}</td>
                <td class="col-md-2 text-center">${news.date}</td>
                <td class="col-md-2 text-center">${news.title}</td>
                <td class="col-md-2 text-center">${news.shortDescription}</td>
                <td class="col-md-5 text-center">${news.text}</td>
            </tr>`;

    return table;
};

function filterByDate() {
    let date = document.getElementById('filterByDate').value;
    getAllNews("", "date", date);
    $('#filterByDate').val("");
}

function filterByTitle() {
    let title = document.getElementById('filterByTitle').value;
    getAllNews("", "title", title);
    $('#filterByTitle').val("");
}

function filterByDateAndTitle() {
    let date = document.getElementById('filterByDate').value;
    let title = document.getElementById('filterByTitle').value;
    console.log(date + "And" + title);
    getAllNews("", "dateAndTitle", date + "And" + title);
    $('#filterByDate').val("");
    $('#filterByTitle').val("");
}
