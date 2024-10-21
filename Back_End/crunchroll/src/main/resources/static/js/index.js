
if (typeof jQuery !== 'undefined') {
    console.log("jQuery está carregado!");
} else {
    console.error("jQuery não está definido.");
}

$('.owl-carousel').owlCarousel({

    margin:10,
    nav:true,
    responsive:{
        0:{
            items:2
        },
        600:{
            items:3
        },
        1000:{
            items:5
        }
    }
})




document.addEventListener("DOMContentLoaded", function() {
    const maxChars = 300;

    function setupTextToggle(textId, moreTextId) {
        const paragraph = document.getElementById(textId);
        const moreText = document.getElementById(moreTextId);
        const originalText = paragraph.innerHTML;

        if (originalText.length > maxChars) {
            const visibleText = originalText.slice(0, maxChars) + '...';
            paragraph.innerHTML = visibleText;
            moreText.innerHTML = originalText.slice(maxChars);
        } else {
            moreText.innerHTML = '';
            moreText.classList.add('hidden'); // Se o texto for menor que 200 caracteres
            return; // Para evitar o erro de botão se não houver texto
        }

        const toggleButton = document.createElement("button");
        toggleButton.textContent = "Mostrar Mais";
        toggleButton.className = "btn btn-secondary"; // Estilo do botão
        toggleButton.addEventListener("click", function() {
            if (moreText.classList.contains("hidden")) {
                moreText.classList.remove("hidden");
                toggleButton.textContent = "Mostrar Menos";
            } else {
                moreText.classList.add("hidden");
                toggleButton.textContent = "Mostrar Mais";
            }
        });
        paragraph.parentElement.appendChild(toggleButton);
    }

    // Configura os textos para cada slide
    setupTextToggle("myText1", "moreText1");
    setupTextToggle("myText2", "moreText2");
    setupTextToggle("myText3", "moreText3");
});
