// initialize variables (Khởi tạo biến)
let pointMake = null;
const API_KEY_TOMTOM = "dRrxO1yXAGX2V9Ee1RSxqG6qT0Gb64j3";
const API_KEY_AQI = "40e5dcd4ef8dc3c01daf8e51936235f7c3f471f9";
const arrCpModel = [];
// querySelector(bộ chọn truy vấy): truy vấn với tới class or id của tags
const inputShowLat = document.querySelector("#inputShowLat");
const inputShowLng = document.querySelector("#inputShowLng");
const inputShowAddress = document.querySelector("#inputShowAddress");
const chartTrafficSpeed = document.querySelector("#ChartTrafficSpeed");
const cpModalTraffic = document.querySelector("#modal-traffic");
const cpModalDeleteTraffic = document.querySelector("#modal-delete-traffic");
const cpModalDetailTS = document.querySelector("#modal-detail-TS");
const BmodalTraffic = new bootstrap.Modal(cpModalTraffic);
const BmodalDeleteTraffic = new bootstrap.Modal(cpModalDeleteTraffic);
const BmodalDetailTS = new bootstrap.Modal(cpModalDetailTS);
const getModalTraffic = bootstrap.Modal.getInstance(cpModalTraffic);
const formTraffic = document.querySelector("#trafficForm");
const titleModelTraffic = document.querySelector("#modalTitle");
const btnSubmitTraffic = document.querySelector("#SubmitTrafficInfo");
const cpInputTrafficId = document.querySelector("#cpInputTrafficId");
const inputShowId = document.querySelector("#inputShowID");
const formDeleteTraffic = document.querySelector("#trafficFormDelete");
const spanTrafficInfoId = document.querySelector("#trafficinfoId");
// lấy bản đồ
const map = L.map("map").setView([21.0285, 105.8542], 12);
L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png", {
  maxZoom: 19,
  attribution:
    '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
}).addTo(map);

// hàm click lấy điểm tọa độ point: latitude và longitude
const onMapClick = async (e) => {
  const lat = e.latlng.lat;
  const lng = e.latlng.lng;
  if (pointMake) {
    // kill ponit
    map.removeLayer(pointMake);
  }
  pointMake = L.marker([lat, lng])
    .addTo(map)
    .bindPopup(`Lat: ${lat}<br>Lng: ${lng}`)
    .openPopup();
  try {
    const response = await axios.get(
      `https://nominatim.openstreetmap.org/reverse?format=json&lat=${lat}&lon=${lng}`,
    );

    const result = response.data.display_name;
    if (getModalTraffic?._isShown) {
      inputShowLat.value = lat;
      inputShowLng.value = lng;
      inputShowAddress.value = result;
    }
  } catch (err) {
    console.error("Error: get address:", err);
    return "Error";
  }
};
map.on("click", onMapClick);

// hàm xử lý biểu đồ
new Chart(chartTrafficSpeed, {
  type: "line",
  data: {
    labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
    datasets: [
      {
        label: "# of Votes",
        data: [12, 19, 3, 5, 2, 3],
        borderWidth: 1,
      },
    ],
  },
  options: {
    scales: {
      y: {
        beginAtZero: true,
      },
    },
  },
});
// hàm mở modal traffic
function openCreateTraffic() {
  BmodalTraffic.show();
  titleModelTraffic.textContent = "Create Traffic";
  btnSubmitTraffic.textContent = "Create traffic";
  // formTraffic.setAttribute("th:action", "@{/add-traffic}");
  formTraffic.action = "/add-traffic";
}
function openUpdateTraffic(value) {
  inputShowId.value = value.dataset.id;
  inputShowLat.value = value.dataset.lat;
  inputShowLng.value = value.dataset.lng;
  inputShowAddress.value = value.dataset.address;
  BmodalTraffic.show();
  titleModelTraffic.textContent = "Update Traffic";
  btnSubmitTraffic.textContent = "Update traffic";
  cpInputTrafficId.style = "display:flex";
  formTraffic.action = `/update/${value.dataset.id}`;
  pointMake = L.marker([value.dataset.lat, value.dataset.lng])
    .addTo(map)
    .bindPopup(`Lat: ${value.dataset.lat}<br>Lng: ${value.dataset.lng}`)
    .openPopup();
}

function openDeleteTraffic(value) {
  BmodalDeleteTraffic.show();
  formDeleteTraffic.action = `/delete/${value.dataset.id}`;
}

let currentTrafficId = null;
let currentPage = 0;
const pageSize = 5;

async function openDetailTrafficSpeed(value, page = 0) {
  currentTrafficId = value.dataset.id;

  currentPage = page;

  spanTrafficInfoId.textContent = `Traffic-Info-ID: ${currentTrafficId}`;

  BmodalDetailTS.show();

  try {
    const res = await fetch(
      `/get-traffic-info/${currentTrafficId}?page=${page}&size=${pageSize}`,
    );

    const data = await res.json();

    renderTrafficTable(data);

    renderPagination(data);
  } catch (err) {
    console.error(err);
  }
}

function renderTrafficTable(data) {
  const tbody = document.querySelector("#modal-detail-TS tbody");

  tbody.innerHTML = "";

  let html = "";

  data.content.forEach((v, index) => {
    const date = new Date(v.createdAt);

    const formatted = date.toLocaleString("vi-VN", {
      day: "2-digit",
      month: "2-digit",
      year: "numeric",
      hour: "2-digit",
      minute: "2-digit",
    });
    html += `

<tr>

<td>
${data.number * data.size + index + 1}
</td>

<td>
${v.trafficSpeedId}
</td>

<td>
${v.frc}
</td>

<td>
${v.currentSpeed}
</td>

<td>
${v.freeFlowSpeed}
</td>

${v.trafficLevel == "LOW" ? '<td style="color:blue">LOW</td>' : v.trafficLevel == "MEDIUM" ? '<td style="color:yellow">MEDIUM</td>' : '<td style="color:red">HIGH</td>'}

<td>
${v.currentTravelTime}
</td>

<td>
${v.freeFlowTravelTime}
</td>

<td>
${v.confidence}
</td>

<td>
${v.roadClosure}
</td>

<td>
${formatted}
</td>
</tr>

`;
  });

  tbody.innerHTML = html;
}

function renderPagination(data) {
  let pagination = document.querySelector("#paginationTrafficSpeed");

  if (!pagination) {
    const nav = document.createElement("nav");

    nav.innerHTML = `
<ul
id="paginationTrafficSpeed"
class="
pagination
justify-content-end
"
data-bs-theme="dark">
</ul>
`;

    document.querySelector("#modal-detail-TS .modal-body").appendChild(nav);

    pagination = document.querySelector("#paginationTrafficSpeed");
  }

  pagination.innerHTML = "";

  // PREVIOUS

  pagination.innerHTML += `
<li
class="
page-item
${data.first ? "disabled" : ""}
">

<button
class="page-link"
onclick="
changePage(
${data.number - 1}
)
">

Previous

</button>

</li>
`;

  const current = data.number;

  const total = data.totalPages;

  const pages = new Set();

  pages.add(0);

  for (let i = current - 2; i <= current + 2; i++) {
    if (i >= 0 && i < total) {
      pages.add(i);
    }
  }

  pages.add(total - 1);

  const sorted = [...pages].sort((a, b) => a - b);

  let prev = -1;

  sorted.forEach((page) => {
    if (page - prev > 1) {
      pagination.innerHTML += `

<li
class="
page-item
disabled
">

<span
class="
page-link
">

...

</span>

</li>

`;
    }

    pagination.innerHTML += `

<li
class="
page-item
${page === current ? "active" : ""}
">

<button
class="
page-link
"

onclick="
changePage(
${page}
)
"

>

${page + 1}

</button>

</li>

`;

    prev = page;
  });

  // NEXT

  pagination.innerHTML += `

<li
class="
page-item
${data.last ? "disabled" : ""}
">

<button
class="
page-link
"

onclick="
changePage(
${data.number + 1}
)
"

>

Next

</button>

</li>

`;
}

function changePage(page) {
  openDetailTrafficSpeed(
    {
      dataset: {
        id: currentTrafficId,
      },
    },
    page,
  );
}

// hàm đóng modal traffic info
arrCpModel.push(cpModalTraffic, cpModalDeleteTraffic, cpModalDetailTS);
arrCpModel.map((value, index) =>
  value.addEventListener("hide.bs.modal", () => {
    document.activeElement?.blur();
  }),
);
cpModalTraffic.addEventListener("hidden.bs.modal", () => {
  pointMake?.remove();
  formTraffic.reset();
  cpInputTrafficId.style = "display:none";
});
