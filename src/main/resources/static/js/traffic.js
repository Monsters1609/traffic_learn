// initialize variables (Khởi tạo biến)
let pointMake = null;
const API_KEY_TOMTOM = "dRrxO1yXAGX2V9Ee1RSxqG6qT0Gb64j3";
const API_KEY_AQI = "40e5dcd4ef8dc3c01daf8e51936235f7c3f471f9";
// querySelector(bộ chọn truy vấy): truy vấn với tới class or id của tags
const inputShowLat = document.querySelector("#inputShowLat");
const inputShowLng = document.querySelector("#inputShowLng");
const inputShowAddress = document.querySelector("#inputShowAddress");
const chartTrafficSpeed = document.querySelector("#ChartTrafficSpeed");
const cpModalTraffic = document.querySelector("#modal-traffic");
const cpModalDeleteTraffic = document.querySelector("#modal-delete-traffic");
const BmodalTraffic = new bootstrap.Modal(cpModalTraffic);
const BmodalDeleteTraffic = new bootstrap.Modal(cpModalDeleteTraffic);
const getModalTraffic = bootstrap.Modal.getInstance(cpModalTraffic);
const getModalDeleteTraffic = bootstrap.Modal.getInstance(cpModalDeleteTraffic);
const formTraffic = document.querySelector("#trafficForm");
const titleModelTraffic = document.querySelector("#modalTitle");
const btnSubmitTraffic = document.querySelector("#SubmitTrafficInfo");
const cpInputTrafficId = document.querySelector("#cpInputTrafficId");
const inputShowId = document.querySelector("#inputShowID");
const formDeleteTraffic = document.querySelector("#trafficFormDelete");
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

function openPopupDeleteTraffic(value) {
  BmodalDeleteTraffic.show();
  formDeleteTraffic.action = `/delete/${value.dataset.id}`;
}
// hàm đóng modal traffic info
cpModalTraffic.addEventListener("hide.bs.modal", () => {
  document.activeElement?.blur();
});
cpModalDeleteTraffic.addEventListener("hide.bs.modal", () => {
  document.activeElement?.blur();
});
cpModalTraffic.addEventListener("hidden.bs.modal", () => {
  pointMake?.remove();
  formTraffic.reset();
  cpInputTrafficId.style = "display:none";
});
