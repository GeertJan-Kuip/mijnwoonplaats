document.addEventListener("DOMContentLoaded", () => {
    const input = document.getElementById("searchInput");
    const suggestionsBox = document.getElementById("suggestions");
    const resultBox = document.getElementById("result");

    let debounceTimer;

    // helper: extract simple name (first part of extended name)
    function getSimpleName(extendedName) {
        return extendedName.split(",")[0].trim();
    }

    // helper: update URL without reloading
    function updateUrl(simpleName, id) {
        const encodedName = encodeURIComponent(simpleName);
        window.history.pushState({}, "", `/info/${encodedName}/${id}`);
    }

    // fetch result content from backend
    async function showResult(id) {
        try {
            const response = await fetch(`/api/town/${encodeURIComponent(id)}`);
            if (!response.ok) throw new Error("Network response was not ok");
            const html = await response.text();
            resultBox.innerHTML = html;
        } catch (err) {
            console.error("Error fetching town info:", err);
            resultBox.innerHTML = "<p>Tonen woonplaatsdata niet mogelijk. Probeer een nieuwe zoekopdracht.</p>";
        }
    }

    // fetch suggestions from backend
    async function fetchSuggestions(query) {
        suggestionsBox.innerHTML = "";
        suggestionsBox.style.display = "none";
        resultBox.innerHTML = "";

        if (query.length >= 3) {
            try {
                const response = await fetch(`/api/suggestions?q=${encodeURIComponent(query)}`);
                if (!response.ok) throw new Error("Network response was not ok");
                const matches = await response.json(); // [{id, name}, ...]

                if (matches.length > 0) {
                    suggestionsBox.style.display = "block";
                    matches.forEach(match => {
                        const item = document.createElement("div");
                        item.className = "suggestion-item";
                        item.textContent = match.name; // show extended name

                        item.addEventListener("click", () => {
                            const simpleName = getSimpleName(match.name);
                            input.value = simpleName;
                            suggestionsBox.style.display = "none";
                            updateUrl(simpleName, match.id);
                            showResult(match.id);
                        });

                        suggestionsBox.appendChild(item);
                    });
                } else {
                    suggestionsBox.style.display = "block";
                    suggestionsBox.textContent = "No suggestions found.";
                }
            } catch (err) {
                console.error("Error fetching suggestions:", err);
            }
        }
    }

    // debounce input events
    input.addEventListener("input", () => {
        clearTimeout(debounceTimer);
        const query = input.value.trim();
        debounceTimer = setTimeout(() => {
            fetchSuggestions(query);
        }, 300);
    });

    // hide suggestions when clicking outside
    document.addEventListener("click", (e) => {
        if (!e.target.closest(".search-wrapper")) {
            suggestionsBox.style.display = "none";
        }
    });

    // check if page loaded with /info/{simpleName}/{id} URL
    const pathParts = window.location.pathname.split("/");
    if (pathParts.length === 4 && pathParts[1] === "info") {
        const simpleName = decodeURIComponent(pathParts[2]);
        const id = pathParts[3];
        input.value = simpleName; // pre-fill search box
        showResult(id);           // load result from backend
    }
});
